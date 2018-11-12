package code;

/**
 * https://leetcode.com/problems/surface-area-of-3d-shapes/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-08 14:47
 */
public class SurfaceAreaOf3DShapes {

    public int surfaceArea(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;

        int result = 0;

        for(int i = 0; i < rowLen; i++) {
            for(int j = 0; j < colLen; j++) {
                int height = grid[i][j];
                if(height == 0) {
                    continue;
                }
                result += height * 6 - (height - 1) * 2;

                //平面上的右下相邻位置是否有立方体
                if(i + 1 < rowLen) {
                    int nextRowHeight = grid[i+1][j];
                    result -= 2 * Math.min(nextRowHeight, height);
                }

                if(j + 1 < colLen) {
                    int nextColHeight = grid[i][j+1];
                    result -= 2 * Math.min(nextColHeight, height);
                }
            }
        }
        return result;
    }

}
