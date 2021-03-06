package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-19 17:42
 */
public class DecodeString {

    /**
     * Recursive
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s.indexOf('[') == -1) {
            return s;
        }

        int balance = 0;
        int countStart = -1;
        int bracketStart = 0;
        int bracketEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (countStart == -1 && Character.isDigit(c)) {
                countStart = i;
            }
            if (c == '[') {
                if (balance == 0) {
                    bracketStart = i;
                }
                balance++;
            }
            if (c == ']') {
                balance--;
                if (balance == 0) {
                    bracketEnd = i;
                    break;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(s, 0, countStart);
        for (int i = 0; i < Integer.parseInt(s.substring(countStart, bracketStart)); i++) {
            builder.append(decodeString(s.substring(bracketStart + 1, bracketEnd)));
        }
        return builder.append(decodeString(s.substring(bracketEnd + 1))).toString();
    }

    /**
     * Iterative using stack
     *
     * @param s
     * @return
     */
    public String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        Deque<String> resStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if(Character.isDigit(c)) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(count);
            } else if (c == '[') {
                resStack.push(res.toString());
                res = new StringBuilder();
                index++;
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int count = countStack.pop();
                for(int i = 0; i < count; i++) {
                    temp.append(res);
                }
                res = temp;
                index++;
            } else {
                res.append(c);
                index++;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(new DecodeString().decodeString(s));
    }

}
