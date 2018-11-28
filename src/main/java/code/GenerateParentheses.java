package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-20 10:36
 */
public class GenerateParentheses {

    /**
     * backtracking
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max) {
            backtrack(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(ans, cur + ")", open, close + 1, max);
        }
    }

    /**
     * Recursive / Closure Number
     *
     * To enumerate something, generally we would like to express it as a sum of
     * disjoint subsets that are easier to neighborCount.
     *
     * Consider the closure number of a valid parentheses sequence S: the least index >= 0
     * so that S[0], S[1], ..., S[2*index+1] is valid. Clearly, every parentheses sequence has a unique closure number.
     * We can try to enumerate them individually.
     *
     * For each closure number c, we know the starting and ending brackets must be at index 0 and 2*c + 1.
     * Then, the 2*c elements between must be a valid sequence, plus the rest of the elements must be a valid sequence.
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();
        if(n == 0) {
            ans.add("");
            return ans;
        }

        //根据左起第一个左括号所匹配的右括号的位置对解的所有情况进行分类, 这个右括号的位置从1到2n，并且这对括号中间的部分也是valid的
        // '(' + 合法的i对括号 + ')' + 剩余的n-1-i对括号 , i >= 0 && i < n
        for(int i = 0; i < n; i++) {
            for(String left : generateParenthesis1(i)) {
                for(String right : generateParenthesis1(n - 1 - i)) {
                    ans.add("(" + left + ")" + right);
                }
            }
        }
        return ans;
    }

}
