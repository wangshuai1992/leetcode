package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-21 15:24
 */
public class RemoveInvalidParentheses {

    private Set<String> validExpressions = new HashSet<>();
    private int minimumRemoved = Integer.MAX_VALUE;

    /**
     * backtracking / O(2^N) time, O(N) space
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList<>(this.validExpressions);
    }

    private void recurse(String s, int index, int leftCount, int rightCount, StringBuilder expression, int removedCount) {
        // If we have reached the end of string.
        if (index == s.length()) {
            // If the current expression is valid
            // and the current count of removed parentheses is <= the current minimum count
            if (leftCount == rightCount && removedCount <= this.minimumRemoved) {
                // Convert StringBuilder to a String. This is an expensive operation.
                // So we only perform this when needed.
                String possibleAnswer = expression.toString();
                // If the current count beats the overall minimum we have till now
                if (removedCount < this.minimumRemoved) {
                    this.validExpressions.clear();
                    this.minimumRemoved = removedCount;
                }
                this.validExpressions.add(possibleAnswer);
            }
        } else {
            char currentCharacter = s.charAt(index);
            int length = expression.length();
            // If the current character is neither an opening bracket nor a closing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {
                // Recursion where we delete the current character and move forward
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                expression.append(currentCharacter);
                // If it's an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if right < left
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }
                // Undoing the append operation for other recursions.
                expression.deleteCharAt(length);
            }
        }
    }

    private char[] chars;
    private Set<String> result = new HashSet<>();
    private int left;
    private int right;

    /**
     * backtracking with limit / O(2^N) time, O(N) space
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses1(String s) {
        chars = s.toCharArray();
        int balance = 0;
        //calculate how many displaced '(' and ')'
        for (char c : chars) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    balance = 0;
                    right++;
                }
            }
        }
        left = balance;

        backtrack1(0, new StringBuilder(), 0, 0, 0);
        return new ArrayList<>(result);
    }

    private void backtrack1(int index, StringBuilder temp, int tempLeft, int tempRight, int balance) {
        if (index == chars.length) {
            if(balance == 0) {
                result.add(temp.toString());
            }
        } else {
            char c = chars[index];

            //discard
            if (c == '(' && tempLeft < left) {
                backtrack1(index + 1, temp, tempLeft + 1, tempRight, balance);
            } else if (c == ')' && tempRight < right) {
                backtrack1(index + 1, temp, tempLeft, tempRight + 1, balance);
            }

            //take
            if (c == '(') {
                backtrack1(index + 1, temp.append(c), tempLeft, tempRight, balance + 1);
                temp.deleteCharAt(temp.length() - 1);
            } else if (c == ')' && balance - 1 >= 0) {
                backtrack1(index + 1, temp.append(c), tempLeft, tempRight, balance - 1);
                temp.deleteCharAt(temp.length() - 1);
            } else if (c != ')') {
                //neither '(' nor ')'
                backtrack1(index + 1, temp.append(c), tempLeft, tempRight, balance);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

}
