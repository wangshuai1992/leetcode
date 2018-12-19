package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-18 10:11
 */
public class MinimumPathSum {

    /**
     * backtrack with memo
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        return backtrack(grid, 0, 0, memo);
    }

    /**
     * this method returns the minimum path sum you can get when you start from point (x, y)
     */
    private int backtrack(int[][] grid, int x, int y, int[][] memo) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }

        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return grid[x][y];
        }

        int min = Integer.MAX_VALUE;
        if (x < grid.length - 1) {
            min = Math.min(backtrack(grid, x + 1, y, memo), min);
        }
        if (y < grid[0].length - 1) {
            min = Math.min(backtrack(grid, x, y + 1, memo), min);
        }
        min += grid[x][y];
        memo[x][y] = min;
        return min;
    }

    /**
     * Dynamic Programming
     *
     * dp[i][j] means the min path you can get when start from grid[i][j]
     *
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {
        int xLen = grid.length;
        int yLen = grid[0].length;
        int[][] dp = new int[xLen][yLen];
        for(int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        dp[xLen - 1][yLen - 1] = grid[xLen - 1][yLen - 1];
        for(int i = xLen - 1; i >= 0; i--) {
            for(int j = yLen - 1; j >= 0; j--) {
                if(i != 0) {
                    dp[i - 1][j] = Math.min(dp[i - 1][j], grid[i - 1][j] + dp[i][j]);
                }
                if(j != 0) {
                    dp[i][j - 1] = Math.min(dp[i][j - 1], grid[i][j - 1] + dp[i][j]);
                }
            }
        }
        return dp[0][0];
    }

}
