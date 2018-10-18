package code;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-15 09:29
 */
public class MinimumAddToMakeParenthesesValid {

    public int minAddToMakeValid(String s) {
        int result = 0;

        char[] chars = s.toCharArray();
        Deque<Character> deque = new LinkedList<>();
        for (char c : chars) {
            deque.addLast(c);
        }

        Deque<Character> temp = new LinkedList<>();
        //从尾部往前就近匹配
        while (!deque.isEmpty()) {
            char c = deque.removeLast();
            if (deque.isEmpty() || c == '(') {
                result++;
                continue;
            }

            boolean isMatched = false;
            while (!deque.isEmpty()) {
                char c1 = deque.removeLast();
                if (c1 == '(') {
                    isMatched = true;
                    break;
                } else {
                    temp.addFirst(c1);
                }
            }

            if (!isMatched) {
                result++;
            }

            deque.addAll(temp);
            temp.clear();
        }

        return result;
    }

    /**
     * Keep track of the balance of the string: the number of '(''s minus the number of ')''s.
     *
     * A string is valid if its balance is 0, plus every prefix has non-negative balance.
     *
     * @param s
     * @return
     */
    public int minAddToMakeValid1(String s) {
        int ans = 0;
        int balance = 0;
        //从左往右匹配 则(为1 )为-1
        for (int i = 0; i < s.length(); ++i) {
            balance += s.charAt(i) == '(' ? 1 : -1;
            //guarante bal >= -1 多出的')'直接算入结果
            if (balance == -1) {
                ans ++;
                balance = 0;
            }
        }

        return ans + balance;
    }

    public static void main(String[] args) {
        String test = "((())";
        System.out.println(new MinimumAddToMakeParenthesesValid().minAddToMakeValid(test));
    }

}
