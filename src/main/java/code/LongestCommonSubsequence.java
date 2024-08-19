package code;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-09 22:21
 */
public class LongestCommonSubsequence {

    /**
     * recursive
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        return recurse(text1, text2, 0, 0);
    }

    /**
     * 返回从下标开始到末尾的子串的最长公共子序列
     *
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @return
     */
    private int recurse(String s1, String s2, int i, int j) {
        if (i > s1.length() - 1 || j > s2.length() - 1) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return recurse(s1, s2, i + 1, j + 1) + 1;
        }
        return Math.max(recurse(s1, s2, i + 1, j), recurse(s1, s2, i, j + 1));
    }

    /**
     * recursive with memo
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        Integer[][] memo = new Integer[text1.length() + 1][text2.length() + 1];
        return recurse(text1, text2, 0, 0, memo);
    }

    private int recurse(String s1, String s2, int i, int j, Integer[][] memo) {
        if (i > s1.length() - 1 || j > s2.length() - 1) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            if (memo[i + 1][j + 1] == null) {
                memo[i + 1][j + 1] = recurse(s1, s2, i + 1, j + 1, memo);
            }
            return memo[i + 1][j + 1] + 1;
        }
        if (memo[i][j + 1] == null) {
            memo[i][j + 1] = recurse(s1, s2, i, j + 1, memo);
        }
        if (memo[i + 1][j] == null) {
            memo[i + 1][j] = recurse(s1, s2, i + 1, j, memo);
        }
        return Math.max(memo[i][j + 1], memo[i + 1][j]);
    }

    /**
     * dp
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        // dp[i][j]代表text1从开始至下标i - 1, text2从开始至下标j - 1的最长公共子序列
        // dp[0][x]和dp[y][0]代表其中一个是空串，值都默认为0
        int dp[][] = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp [i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

}
