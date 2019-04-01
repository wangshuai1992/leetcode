package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-22 16:29
 */
public class ValidParentheses {

    /**
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            //If it is an opening bracket, push to the stack.
            if (isOpening(c)) {
                stack.push(c);
                continue;
            }

            //closing bracket
            if (stack.isEmpty()) {
                return false;
            }
            if (!isPair(stack.pop(), c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean isOpening(char c) {
        return c != ')' && c != ']' && c != '}';
    }

    private boolean isPair(char a, char b) {
        if (a == '(') {
            return b == ')';
        }
        if (a == '[') {
            return b == ']';
        }
        return a == '{' && b == '}';
    }

    /**
     *
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
