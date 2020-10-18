import java.lang.reflect.Array;
import java.util.*;

public class HashListAutocomplete implements Autocompletor {
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    @Override
    public List<Term> topMatches(String prefix, int k) {
        return null;
    }

    public HashListAutocomplete(String[] terms, double[] weights) {
        if (terms == null || weights == null) {
            throw new NullPointerException("One or more arguments null");
        }

        if (terms.length != weights.length) {
            throw new IllegalArgumentException("terms and weights are not the same length");
        }
        initialize(terms, weights);
    }

    @Override
    public void initialize(String[] terms, double[] weights) throws IllegalArgumentException {
        for (int i = 0; i < terms.length; i++) {
            Term prefixTerm = new Term(terms[i].substring(0, MAX_PREFIX), 0);
            ArrayList<Term> termList = new ArrayList<>();
            //myMap.put(terms[i].substring(0, MAX_PREFIX), )
        }
    }

    @Override
    public int sizeInBytes() {
        return 0;
    }
}
