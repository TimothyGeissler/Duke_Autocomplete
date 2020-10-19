import java.util.*;

public class HashListAutocomplete implements Autocompletor {

    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    /**
     * Given arrays of words and weights, initialize myTerms to a corresponding
     * array of Terms sorted lexicographically.
     *
     * @param terms
     *            - A list of words to form terms from
     * @param weights
     *            - A corresponding list of weights, such that terms[i] has
     *            weight[i].
     * @return a HashListAutocomplete object
     * @throws NullPointerException if either argument passed in is null
     */
    public HashListAutocomplete(String[] terms, double[] weights){
        if (terms == null || weights == null) {
            throw new NullPointerException("One or more arguments null");
        }
        if (terms.length != weights.length) {
            throw new IllegalArgumentException("terms and weights are not the same length");
        }
        initialize (terms, weights);
    }

    /**
     * If the prefix is in the map, get the top k matching terms in the order of descending weight
     * @param prefix
     * @param k
     * @return a sublist of the top k matching terms in descending weight
     */
    @Override
    public List<Term> topMatches(String prefix, int k) {
        if (prefix.length() > MAX_PREFIX){
            prefix = prefix.substring(0, MAX_PREFIX);
        }
        if (!myMap.containsKey(prefix)){
            return new ArrayList<>();
        }
        List<Term> all = myMap.get(prefix);
        List<Term> list = all.subList(0, Math.min(k, all.size()));
        return list;
    }

    /**
     * Given arrays of terms and their weights, initializes the HashMap
     * The key in the map is a prefix/substring
     * The value for each prefix key is a weight-sorted list of Term objects sharing the same prefix
     * The values in the map are maintained sorted from high to low by weight
     * @param terms is array of Strings for words in each Term
     * @param weights is corresponding weight for word in terms
     */
    @Override
    public void initialize(String[] terms, double[] weights) {

        myMap = new HashMap<>();

        for (int m = 0; m < terms.length; m++) {
            int prefixSize = Math.min (terms[m].length(), MAX_PREFIX);
            for (int n = 0; n <= prefixSize; n++){
                String key = terms[m].substring(0, n);

                if (!myMap.containsKey(key)){
                    myMap.put (key, new ArrayList<Term>());
                }
                myMap.get(key).add (new Term (terms[m], weights[m]));
            }
        }

        for (String s: myMap.keySet()){
            Collections.sort(myMap.get(s),Comparator.comparing(Term::getWeight).reversed());
        }
    }

    /**
     * Return size in bytes of all Strings and doubles
     * stored in implementing class. To the extent that
     * other types are used for efficiency, there size should
     * be included too
     * @return number of bytes used after initialization
     */

    @Override
    public int sizeInBytes() {
        if (mySize == 0) {

            for(String s : myMap.keySet()) {
                mySize += BYTES_PER_CHAR*s.length();
                for (Term t: myMap.get(s)){
                    mySize += BYTES_PER_DOUBLE+BYTES_PER_CHAR*t.getWord().length();
                }
            }
        }
        return mySize;
    }
}
