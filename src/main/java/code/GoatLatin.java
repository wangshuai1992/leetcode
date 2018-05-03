package code;

/**
 * https://leetcode.com/problems/goat-latin/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-29 17:07
 */
public class GoatLatin {

    public String toGoatLatin(String S) {
        StringBuilder result = new StringBuilder();

        String[] words = S.split(" ");

        for(int i=0; i<words.length; i++) {
            String w = words[i];
            if(w.startsWith("a") || w.startsWith("e") || w.startsWith("i") || w.startsWith("o") || w.startsWith("u") ||
                    w.startsWith("A") || w.startsWith("E") || w.startsWith("I") || w.startsWith("O") || w.startsWith("U")) {
                result.append(w);
            } else {
                result.append(w.substring(1)).append(w.charAt(0));
            }
            result.append("ma");
            for(int j=0; j<=i; j++) {
                result.append('a');
            }
            result.append(' ');
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static void main(String[] args) {
        String s = "The quick brown fox jumped over the lazy dog";
        System.out.println(new GoatLatin().toGoatLatin(s));
    }

}
