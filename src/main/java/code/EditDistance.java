package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/edit-distance/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-28 16:32
 */
public class EditDistance {

    /**
     * Recursive
     *
     * For each poisition, check three subproblem:
     * 1. insert
     * 2. delete
     * 3. replace
     * We only modify the first string since no matter which one we choose, the result is the same.
     *
     * recursively solve the same subproblem several times. Approximately O(len1 ^ 3) time in the worst case.
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        return match(word1, word2, 0, 0);
    }

    private int match(String word1, String word2, int i, int j) {
        //If one of the string's pointer have reached the end of it
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            //If current poisition is the same.
            return match(word1, word2, i + 1, j + 1);
        }

        int insert = match(word1, word2, i, j + 1);
        int delete = match(word1, word2, i + 1, j);
        int replace = match(word1, word2, i + 1, j + 1);
        return Math.min(insert, Math.min(delete, replace)) + 1;
    }

    /**
     * Recurse with memorization / O(len1 * len2) time, O(len1 * len2) space
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance1(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        int[][] memo = new int[word1.length()][word2.length()];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return match1(word1, word2, 0, 0, memo);
    }

    private int match1(String word1, String word2, int i, int j, int[][] memo) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = match1(word1, word2, i + 1, j + 1, memo);
        } else {
            int insert = match1(word1, word2, i, j + 1, memo);
            int delete = match1(word1, word2, i + 1, j, memo);
            int replace = match1(word1, word2, i + 1, j + 1, memo);
            res = 1 + Math.min(insert, Math.min(delete, replace));
        }
        memo[i][j] = res;
        return res;
    }

    /**
     * DP / O(len1 * len2) time, O(len1 * len2) space
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = len1; i >= 0; i--) {
            for (int j = len2; j >= 0; j--) {
                if (i == len1) {
                    dp[i][j] = len2 - j;
                } else if (j == len2) {
                    dp[i][j] = len1 - i;
                } else if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    int insert = dp[i][j + 1];
                    int delete = dp[i + 1][j];
                    int replace = dp[i + 1][j + 1];
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp[0][0];
    }

    /**
     * DP / O(len1 * len2) time, O(len1 + len2) space
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] pre = new int[n + 1];
        int[] cur = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            pre[j] = j;
        }
        for (int i = 1; i <= m; i++) {
            cur[0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cur[j] = pre[j - 1];
                } else {
                    cur[j] = Math.min(pre[j - 1], Math.min(cur[j - 1], pre[j])) + 1;
                }
            }
            pre = Arrays.copyOf(cur, cur.length);
            Arrays.fill(cur, 0);
        }
        return pre[n];
    }

    /**
     * DP / O(len1 * len2) time, O(n) space
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance4(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int pre;
        int[] cur = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            cur[j] = j;
        }
        for (int i = 1; i <= m; i++) {
            pre = cur[0];
            cur[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = cur[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cur[j] = pre;
                } else {
                    cur[j] = Math.min(pre, Math.min(cur[j - 1], cur[j])) + 1;
                }
                pre = temp;
            }
        }
        return cur[n];
    }

}
