package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/print-binary-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-30 15:54
 */
public class PrintBinaryTree {

    /**
     * Recursive
     *
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        int h = getHeight(root);
        int w = (1 << h) - 1;
        String[][] matrix = new String[h][w];

        for(String[] arr : matrix) {
            Arrays.fill(arr, "");
        }

        recursiveFill(matrix, root, 0, 0, w);

        List<List<String>> result = new ArrayList<>();
        for(String[] arr : matrix) {
            result.add(Arrays.asList(arr));
        }
        return result;
    }

    private void recursiveFill(String[][] matrix, TreeNode root, int row, int left, int right) {
        if(root == null) {
            return;
        }
        matrix[row][(left + right) / 2] = String.valueOf(root.val);
        recursiveFill(matrix, root.left, row + 1, left, (left + right) / 2);
        recursiveFill(matrix, root.right, row + 1, (left + right) / 2 + 1, right);
    }

    private int getHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

}
