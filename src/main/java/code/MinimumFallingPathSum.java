package code;


/**
 * https://leetcode.com/problems/minimum-falling-path-sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-07 16:49
 */
public class MinimumFallingPathSum {

    /**
     * Let dp(r, c) be the minimum total weight of a falling path starting at (r, c) and reaching the bottom row.
     *
     * @param arr
     * @return
     */
    public int minFallingPathSum(int[][] arr) {
        int rowLen = arr.length;
        int colLen = arr[0].length;

        int[][] dp = new int[rowLen][colLen];
        //对于最下面一行 dp中每个值与arr中对应位置的值相同
        for (int j = 0; j < colLen; j++) {
            dp[rowLen - 1][j] = arr[rowLen - 1][j];
        }

        //逐层向上 计算dp中所有值
        for (int i = rowLen - 2; i >= 0; i--) {
            for (int j = 0; j < colLen; j++) {
                int min = Integer.MAX_VALUE;
                if(j - 1 >= 0) {
                    min = Math.min(dp[i + 1][j - 1], min);
                }
                min = Math.min(dp[i+1][j], min);
                if(j + 1 < colLen) {
                    min = Math.min(dp[i+1][j + 1], min);
                }
                dp[i][j] = arr[i][j] + min;
            }
        }

        int result = Integer.MAX_VALUE;
        for(int j = 0; j < colLen; j++) {
            result = Math.min(result, dp[0][j]);
        }
        return result;
    }

}
