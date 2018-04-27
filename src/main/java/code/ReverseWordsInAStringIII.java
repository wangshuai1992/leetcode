package code;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 14:01
 */
public class ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        String str = new StringBuilder(s).reverse().toString();
        String[] words = str.split(" ");

        StringBuilder result = new StringBuilder();
        for(String word : words) {
            result.insert(0, word + " ");
        }
        return result.toString().substring(0, result.length() - 1);
    }

}
