package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-05-05 11:24
 */
public class DeleteOperationForTwoStrings {

    /**
     * longest common subsequence, 不一定连续
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - 2 * lcs(word1, word2, word1.length(), word2.length());
    }

    private int lcs(String s1, String s2, int m, int n) {
        if(m == 0 || n == 0) {
            return 0;
        }
        if(s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        }
        return Math.max(lcs(s1, s2, m - 1, n), lcs(s1, s2, m, n - 1));
    }

    /**
     * longest common subsequence with memo
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance1(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for(int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return word1.length() + word2.length() - 2 * lcs1(word1, word2, word1.length(), word2.length(), memo);
    }

    private int lcs1(String s1, String s2, int m, int n, int[][] memo) {
        if(m == 0 || n == 0) {
            return 0;
        }
        if(memo[m][n] != -1) {
            return memo[m][n];
        }
        if(s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = 1 + lcs1(s1, s2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = Math.max(lcs1(s1, s2, m - 1, n, memo), lcs1(s1, s2, m, n - 1, memo));
        }
        return memo[m][n];
    }

    /**
     * longest common subsequence with dp
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        return word1.length() + word2.length() - 2 * lcs2(word1, word2, word1.length(), word2.length());
    }

    private int lcs2(String s1, String s2, int m, int n) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[i].length; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
