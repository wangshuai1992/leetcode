package code;

/**
 * https://leetcode.com/problems/reverse-string/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 09:50
 */
public class ReverseString {

    /*
    public String reverseString(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();

        for(int i=0; i<len/2; i++) {
            int j = len - i - 1;
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return new String(arr);
    }
    */

    public String reverseString(String s) {
        char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            word[i] = (char) (word[i] ^ word[j]);
            word[j] = (char) (word[i] ^ word[j]);
            word[i] = (char) (word[i] ^ word[j]);
            i++;
            j--;
        }
        return new String(word);
    }

}
