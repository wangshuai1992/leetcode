package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/number-of-islands/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-07 15:41
 */
public class NumberOfIslands {

    /**
     * recursive DFS
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int x = grid.length;
        int y = grid[0].length;

        int count = 0;

        boolean seen[][] = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!seen[i][j] && grid[i][j] == '1') {
                    dfs(grid, i, j, seen);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] seen) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || seen[i][j] || grid[i][j] == '0') {
            return;
        }
        seen[i][j] = true;
        dfs(grid, i + 1, j, seen);
        dfs(grid, i, j + 1, seen);
        dfs(grid, i - 1, j, seen);
        dfs(grid, i, j - 1, seen);
    }

    /**
     * Iterative DFS
     *
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int x = grid.length;
        int y = grid[0].length;

        boolean[][] seen = new boolean[x][y];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int count = 0;
        for (int r0 = 0; r0 < x; r0++) {
            for (int c0 = 0; c0 < y; c0++) {
                if (grid[r0][c0] == '1' && !seen[r0][c0]) {
                    count++;
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;
                    while (!stack.isEmpty()) {
                        int[] node = stack.pop();
                        int r = node[0];
                        int c = node[1];
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < x && 0 <= nc && nc < y && grid[nr][nc] == '1' && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Union Find
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int x = grid.length;
        int y = grid[0].length;
        UF uf = new UF(x, y, grid);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                int p = i * y + j;
                int q;
                if (i > 0 && grid[i - 1][j] == '1') {
                    q = p - y;
                    uf.union(p, q);
                }
                if (i < x - 1 && grid[i + 1][j] == '1') {
                    q = p + y;
                    uf.union(p, q);
                }
                if (j > 0 && grid[i][j - 1] == '1') {
                    q = p - 1;
                    uf.union(p, q);
                }
                if (j < y - 1 && grid[i][j + 1] == '1') {
                    q = p + 1;
                    uf.union(p, q);
                }
            }
        }
        return uf.count;
    }


    private static class UF {

        private int count = 0;
        private int[] id;

        UF(int m, int n, char[][] grid) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
            id = new int[m * n];
            for (int i = 0; i < m * n; i++) {
                id[i] = i;
            }
        }

        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            return pRoot == qRoot;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return;
            }
            id[pRoot] = qRoot;
            count--;
        }
    }

}
