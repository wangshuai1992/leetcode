package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/score-of-parentheses/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-08 17:04
 */
public class ScoreOfParentheses {

    /**
     * Count Cores - O(n)
     *
     * keep track of balance, every core values 1 << balance
     *
     * @param str
     * @return
     */
    public int scoreOfParentheses(String str) {
        int ans = 0;
        //power of current core
        int balance = 0;

        int preChar = ' ';
        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
                if (preChar == '(') {
                    ans += 1 << balance;
                }
            }
            preChar = c;
        }
        return ans;
    }

    /**
     * use stack - O(n)
     *
     * @param str
     * @return
     */
    public int scoreOfParentheses1(String str) {
        Deque<Integer> stack = new ArrayDeque<>();
        // The score of the current frame
        stack.push(0);

        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }

    /**
     * Divide and Couquer - O(n^2)
     *
     * @param str
     * @return
     */
    public int scoreOfParentheses2(String str) {
        int ans = 0;
        int balance = 0;

        // Split string into primitives
        int curSubStrStart = 0;
        for (int i = 0; i < str.length(); ++i) {
            balance += str.charAt(i) == '(' ? 1 : -1;
            if (balance == 0) {
                if (i - curSubStrStart == 1) {
                    ans++;
                } else {
                    ans += 2 * scoreOfParentheses2(str.substring(curSubStrStart + 1, i));
                }
                //calculate next sub string
                curSubStrStart = i + 1;
            }
        }

        return ans;
    }

}
