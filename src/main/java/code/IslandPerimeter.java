package code;

/**
 * https://leetcode.com/problems/island-perimeter/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-10 10:44
 */
public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                int cell = grid[i][j];
                if(cell == 0) {
                    continue;
                }
                perimeter += 4;
                if(i+1 < grid.length && grid[i+1][j] == 1) {
                    perimeter -= 2;
                }
                if(j+1 < grid[i].length && grid[i][j+1] == 1) {
                    perimeter -= 2;
                }
            }
        }
        return perimeter;
    }

}
