package code;

/**
 * https://leetcode.com/problems/construct-quad-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 09:55
 */
public class ConstructQuadTree {

    public Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length);
    }

    /**
     *
     * @param grid
     * @param x start row
     * @param y start col
     * @param size
     * @return
     */
    private Node construct(int[][] grid, int x, int y, int size) {
        if(isLeaf(grid, x, y, size)) {
            Node root = new Node();
            root.val = grid[x][y] == 1;
            root.isLeaf = true;
            return root;
        }

        Node root = new Node();
        root.isLeaf = false;

        int midX = x + size / 2;
        int midY = y + size / 2;
        root.topLeft = construct(grid, x, y, size / 2);
        root.topRight = construct(grid, x, midY, size / 2);
        root.bottomLeft = construct(grid, midX, y, size / 2);
        root.bottomRight = construct(grid, midX, midY, size / 2);
        return root;
    }

    private boolean isLeaf(int[][] grid, int x, int y, int size) {
        int val = grid[x][y];
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(val != grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Node {
        boolean val;
        boolean isLeaf;
        Node topLeft;
        Node topRight;
        Node bottomLeft;
        Node bottomRight;

        public Node() {

        }

        public Node(boolean val, boolean isleaf, Node topleft, Node topright, Node bottomleft, Node bottomright) {
            this.val = val;
            isLeaf = isleaf;
            topLeft = topleft;
            topRight = topright;
            bottomLeft = bottomleft;
            bottomRight = bottomright;
        }
    }

}
