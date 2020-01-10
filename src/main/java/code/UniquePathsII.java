package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-10-22 18:23
 */
public class UniquePathsII {

    /**
     * recursive
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return recurse(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    private int recurse(int[][] obstacleGrid, int m, int n) {
        if (m == 0 && n == 0) {
            return obstacleGrid[0][0] == 1 ? 0 : 1;
        }
        if (m == 0) {
            return obstacleGrid[0][n] == 1 ? 0 : recurse(obstacleGrid, 0, n - 1);
        }
        if (n == 0) {
            return obstacleGrid[m][0] == 1 ? 0 : recurse(obstacleGrid, m - 1, 0);
        }
        return obstacleGrid[m][n] == 1 ? 0 : (recurse(obstacleGrid, m - 1, n) + recurse(obstacleGrid, m, n - 1));
    }

    /**
     * recursive with memory
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int memo[][] = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return recurseWithMemo(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, memo);
    }

    private int recurseWithMemo(int[][] obstacleGrid, int m, int n, int[][] memo) {
        if (memo[m][n] == -1) {
            if (m == 0 && n == 0) {
                memo[m][n] = obstacleGrid[0][0] == 1 ? 0 : 1;
            } else if (m == 0) {
                memo[m][n] = obstacleGrid[0][n] == 1 ? 0 : recurseWithMemo(obstacleGrid, 0, n - 1, memo);
            } else if (n == 0) {
                memo[m][n] = obstacleGrid[m][0] == 1 ? 0 : recurseWithMemo(obstacleGrid, m - 1, 0, memo);
            } else {
                memo[m][n] = obstacleGrid[m][n] == 1 ? 0 : (recurseWithMemo(obstacleGrid, m - 1, n, memo)
                        + recurseWithMemo(obstacleGrid, m, n - 1, memo));
            }
        }
        return memo[m][n];
    }

    /**
     * 2D-DP
     * O(M * N) time, O(M * N) space
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (i > 0 && dp[i - 1][0] == 0) {
                //has obstacle in the front
                dp[i][0] = 0;
            } else {
                dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (j > 0 && dp[0][j - 1] == 0) {
                //has obstacle in the front
                dp[0][j] = 0;
            } else {
                dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
