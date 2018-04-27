package code;

/**
 * https://leetcode.com/problems/max-increase-to-keep-city-skyline/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-27 16:26
 */
public class MaxIncreaseToKeepCitySkyline {

    /**
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int maxIncrease = 0;
        int length = grid.length;
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++) {
                int val = grid[i][j];

                //横向最高
                int max1 = val;

                //纵向最高
                int max2 = val;

                for(int x=0; x<length; x++) {
                    if(grid[i][x] > max1) {
                        max1 = grid[i][x];
                    }
                    if(grid[x][j] > max2) {
                        max2 = grid[x][j];
                    }
                }

                //取横纵最高中较低的一个
                int max = max1 >= max2 ? max2 : max1;
                maxIncrease += max - val;
            }
        }
        return maxIncrease;
    }
     */

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int N = grid.length;
        int[] rowMaxes = new int[N];
        int[] colMaxes = new int[N];

        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c) {
                rowMaxes[r] = Math.max(rowMaxes[r], grid[r][c]);
                colMaxes[c] = Math.max(colMaxes[c], grid[r][c]);
            }

        int ans = 0;
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                ans += Math.min(rowMaxes[r], colMaxes[c]) - grid[r][c];

        return ans;
    }

}
