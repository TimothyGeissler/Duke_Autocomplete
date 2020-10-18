import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Factor pattern for obtaining PrefixComparator objects
 * without calling new. Users simply use
 *
 *     Comparator<Term> comp = PrefixComparator.getComparator(size)
 *
 * @author owen astrachan
 * @date October 8, 2020
 */
public class PrefixComparator implements Comparator<Term> {

    private int myPrefixSize; // size of prefix

    /**
     * private constructor, called by getComparator
     * @param prefix is prefix used in compare method
     */
    private PrefixComparator(int prefix) {
        myPrefixSize = prefix;
    }


    /**
     * Factory method to return a PrefixComparator object
     * @param prefix is the size of the prefix to compare with
     * @return PrefixComparator that uses prefix
     */
    public static PrefixComparator getComparator(int prefix) {
       return new PrefixComparator(prefix);
    }


    @Override
    public int compare(Term v, Term w) {
        int min = Math.min(myPrefixSize, w.getWord().length());
        String stringV = v.getWord().substring(0, Math.min(myPrefixSize, v.getWord().length()));
        String stringW = w.getWord().substring(0, Math.min(myPrefixSize, w.getWord().length()));

        for (int i =0; i < Math.min(stringV.length(), stringW.length()); i++) {
            if (stringV.charAt(i) != stringW.charAt(i)) {
                return stringV.charAt(i) - stringW.charAt(i);
            }
        }
        if (stringV.length() != stringW.length()) {
            return stringV.length() - stringW.length();
        }
        return 0;
        // change this to use my
        // PrefixSize as specified
        /*String wWord = w.getWord();
        String vWord = v.getWord();

        if (wWord.length() > myPrefixSize) {
            wWord = wWord.substring(0, myPrefixSize);
        }
        if (vWord.length() > myPrefixSize) {
            vWord = vWord.substring(0, myPrefixSize);
        }
        System.out.println("(" + myPrefixSize + ") v: " + vWord + ", w: " + wWord);

        for (int i = 0; i < Math.min(vWord.length(), wWord.length()); i++) {
            if (wWord.charAt(i) < vWord.charAt(i)) {
                System.out.println(v.getWord() + " > " + w.getWord());
                return -1;
            } else if (wWord.charAt(i) > vWord.charAt(i)) {
                System.out.println(v.getWord() + " > " + w.getWord());
                return 1;
            }
        }
        System.out.println(v.getWord() + " = " + w.getWord());
        return 0;*/
        /*ArrayList<Integer> min = new ArrayList<>();
        min.add(myPrefixSize);
        min.add(wWord.length());
        min.add(vWord.length());
        Collections.sort(min);
        int minCutoff = min.get(0);
        wWord = wWord.substring(0, minCutoff);
        vWord = vWord.substring(0, minCutoff);


         */
        /*if (wWord.length() < myPrefixSize || vWord.length() < myPrefixSize) {
            System.out.println("Ret -1");
            return -1;
        }
        if (vWord.length() < wWord.length()) { //vWord.length() < myPrefixSize &&
            //v is smaller, cut w
            wWord = wWord.substring(0, vWord.length());
        } else if (wWord.length() < vWord.length()) { //wWord.length() < myPrefixSize &&
            //w is smaller, cut v
            vWord = vWord.substring(0, wWord.length());
        } else if (myPrefixSize <= wWord.length() && myPrefixSize <= vWord.length()) {
            System.out.println("Prefix smallest");
            //prefix smallest
            wWord = wWord.substring(0, myPrefixSize);
            vWord = vWord.substring(0, myPrefixSize);
        }*/

        //System.out.println("min: " + minCutoff + ", prefix: " + myPrefixSize + ", vSize: " + v.getWord().length() + ", wSize: " + w.getWord().length());
        //int comp = vWord.compareTo(wWord);
        /*if (comp < 0) {
            System.out.println(vWord + " < " + wWord);
        } else if (comp > 0) {
            System.out.println(vWord + " > " + wWord);
        } else {
            System.out.println(vWord + " = " + wWord);
        }*/
    }
}
