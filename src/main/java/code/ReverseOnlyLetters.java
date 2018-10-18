package code;

import java.util.*;

/**
 * https://leetcode.com/problems/reverse-only-letters/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-15 15:30
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        char[] newStr = new char[s.length()];
        Deque<Character> stack = new LinkedList<>();
        for(int i=0; i<arr.length; i++) {
            char c = arr[i];
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                stack.push(c);
            } else {
                newStr[i] = c;
            }
        }

        for(int i=0; i<newStr.length; i++) {
            if (newStr[i] == '\u0000') {
                newStr[i] = stack.pop();
            }
        }
        return new String(newStr);
    }

    /**
     * Two Pointer
     *
     * @param s
     * @return
     */
    public String reverseOnlyLetters1(String s) {
        StringBuilder ans = new StringBuilder();
        int j = s.length() - 1;
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isLetter(s.charAt(i))) {
                while (!Character.isLetter(s.charAt(j))) {
                    j--;
                }
                ans.append(s.charAt(j--));
            } else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }

}
