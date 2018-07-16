package code;

/**
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-20 11:16
 */
public class MinimumASCIIDeleteSumForTwoStrings {

    /**
     * Let dp[i][j] be the answer to the problem for the strings s1[i:], s2[j:].（从下标i, j开始到结尾的子串）
     *
     * When one of the input strings is empty, the answer is the ASCII-sum of the other string.
     * We can calculate this cumulatively using code like dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i).
     *
     * When s1[i] == s2[j], we have dp[i][j] = dp[i+1][j+1] as we can ignore these two characters.
     *
     * When s1[i] != s2[j], we will have to delete at least one of them.
     * We'll have dp[i][j] as the minimum of the answers after both deletion options.
     *
     * The solutions presented will use bottom-up dynamic programming.
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            //s1从尾部向前增加字符  s2空串
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.codePointAt(i);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            //s2从尾部向前增加字符  s1空串
            dp[s1.length()][j] = dp[s1.length()][j + 1] + s2.codePointAt(j);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i),
                            dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

}
