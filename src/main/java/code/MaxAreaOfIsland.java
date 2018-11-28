package code;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/max-area-of-island/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-06 16:53
 */
public class MaxAreaOfIsland {

    private int[][] grid;
    private boolean[][] seen;

    public int maxAreaOfIsland1(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                ans = Math.max(ans, area(r, c));
            }
        }
        return ans;
    }

    private int area(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
                seen[r][c] || grid[r][c] == 0) {
            return 0;
        }
        seen[r][c] = true;
        return (1 + area(r + 1, c) + area(r - 1, c) + area(r, c - 1) + area(r, c + 1));
    }

    /**
     * dfs
     *
     * Here, seen will represent squares that have either been visited or are added to our list of
     * squares to visit (stack). For every starting land square that hasn't been visited,
     * we will explore 4-directionally around it, adding land squares that haven't been added to seen or our stack.
     *
     * On the other side, we'll keep a neighborCount shape of the total number of squares seen during the exploration of
     * this shape. We'll want the running max of these counts.
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        for (int r0 = 0; r0 < grid.length; r0++) {
            for (int c0 = 0; c0 < grid[0].length; c0++) {
                if (grid[r0][c0] == 1 && !seen[r0][c0]) {
                    int shape = 0;
                    Deque<int[]> stack = new LinkedList<>();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;
                    while (!stack.isEmpty()) {
                        int[] node = stack.pop();
                        int r = node[0];
                        int c = node[1];
                        shape++;
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid.length && 0 <= nc && nc < grid[0].length && grid[nr][nc] == 1 && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    ans = Math.max(ans, shape);
                }
            }
        }
        return ans;
    }


}
