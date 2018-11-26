package code;


import java.util.*;

/**
 * https://leetcode.com/problems/construct-string-from-binary-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-26 14:50
 */
public class ConstructStringFromBinaryTree {

    /**
     * Recursive
     *
     * @param root
     * @return
     */
    public String tree2str(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return String.valueOf(root.val);
        }
        if (root.right == null) {
            return builder.append(root.val).append('(').append(tree2str(root.left)).append(')').toString();
        }
        return builder.append(root.val).append('(').append(tree2str(root.left)).append(')')
                .append('(').append(tree2str(root.right)).append(')').toString();
    }

    /**
     * Iterative
     *
     * @param root
     * @return
     */
    public String tree2str1(TreeNode root) {
        if (root == null) {
            return "";
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            root = stack.peek();
            if (visited.contains(root)) {
                stack.pop();
                builder.append(")");
            } else {
                visited.add(root);
                builder.append("(").append(root.val);
                if (root.left == null && root.right != null) {
                    builder.append("()");
                }
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        return builder.substring(1, builder.length() - 1);
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
