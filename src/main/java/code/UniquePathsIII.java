package code;

/**
 * https://leetcode.com/problems/unique-paths-iii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-10-22 17:42
 */
public class UniquePathsIII {

    private int ans;
    private int[][] grid;
    private int endRow;
    private int endCol;
    private int[] dr = new int[]{0, -1, 0, 1};
    private int[] dc = new int[]{1, 0, -1, 0};
    private int xLen;
    private int yLen;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        xLen = grid.length;
        yLen = grid[0].length;

        int todo = 0;
        int startRow = 0;
        int startCol = 0;
        for (int row = 0; row < xLen; ++row) {
            for (int col = 0; col < yLen; ++col) {
                if (grid[row][col] != -1) {
                    todo++;
                }

                if (grid[row][col] == 1) {
                    startRow = row;
                    startCol = col;
                } else if (grid[row][col] == 2) {
                    endRow = row;
                    endCol = col;
                }
            }
        }
        ans = 0;
        dfs(startRow, startCol, todo);
        return ans;
    }

    public void dfs(int startRow, int startCol, int todo) {
        todo--;
        if (todo < 0) {
            return;
        }

        if (startRow == endRow && startCol == endCol) {
            if (todo == 0) {
                ans++;
            }
            return;
        }

        grid[startRow][startCol] = 3;
        for (int k = 0; k < 4; ++k) {
            int nextRow = startRow + dr[k];
            int nextCol = startCol + dc[k];
            if (0 <= nextRow && nextRow < xLen && 0 <= nextCol && nextCol < yLen && grid[nextRow][nextCol] % 2 == 0) {
                dfs(nextRow, nextCol, todo);
            }
        }
        grid[startRow][startCol] = 0;
    }

}
