package code;

import java.util.*;

/**
 * https://leetcode.com/problems/friend-circles/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-21 17:38
 */
public class FriendCircles {

    /**
     * non-recursive / DFS
     *
     * @param matrix
     * @return
     */
    public int findCircleNum(int[][] matrix) {
        int count = 0;

        int len = matrix.length;
        boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            Deque<Integer> stack = new ArrayDeque<>();
            //从i开始 深度优先搜索
            stack.push(i);
            while (!stack.isEmpty()) {
                int cur = stack.pop();
                //i之前的好友关系已经被搜索过
                for (int j = i + 1; j < len; j++) {
                    if (matrix[cur][j] == 1 && cur != j && !visited[j]) {
                        visited[j] = true;
                        stack.push(j);
                    }
                }
            }
            count++;
        }
        return count;
    }

    /**
     * Recursive / DFS
     *
     * @param matrix
     * @return
     */
    public int findCircleNum1(int[][] matrix) {
        int[] visited = new int[matrix.length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (visited[i] == 0) {
                dfs(matrix, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] matrix, int[] visited, int i) {
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(matrix, visited, j);
            }
        }
    }

    /**
     * union-find 并查集
     */
    class UnionFind {

        int count;

        int[] parent;

        int[] size;

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            while (parent[p] != p) {
                //路径压缩 path compression
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            count--;
        }

        public int count() {
            return count;
        }

    }

    public int findCircleNum2(int[][] matrix) {
        int len = matrix.length;
        UnionFind uf = new UnionFind(len);

        for (int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                if(matrix[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }

    public static void main(String[] args) {
        int[][] m = new int[3][3];
        m[0] = new int[]{1, 1, 0};
        m[1] = new int[]{1, 1, 0};
        m[2] = new int[]{0, 0, 1};
        System.out.println(new FriendCircles().findCircleNum(m));
    }

}
