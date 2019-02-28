package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-02-27 17:20
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * Recursive / O(n) - O(n^2) time
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }

    /**
     * Recursive  return tail node
     *
     * @param root
     */
    public void flatten1(TreeNode root) {
        recurse(root);
    }

    private TreeNode recurse(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left == null && right == null) {
            return root;
        }
        root.left = null;

        TreeNode leftEnd = recurse(left);
        TreeNode rightEnd = recurse(right);

        if (left != null) {
            root.right = left;
            leftEnd.right = right;
        }
        if (right == null) {
            return leftEnd;
        }
        return rightEnd;
    }

    /**
     * Iterative / using stack
     *
     * move down the tree one level at a time, if the current node has a left child swap it to the right, if the
     * current node has a right child, push it to the stack. If moving to our right would result in a null value,
     * we add the top treenode from our stack to the current node's right. If we run out of nodes, we are done.
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                cur.right = cur.left;
                cur.left = null;
            } else if (cur.right == null && !stack.isEmpty()) {
                cur.right = stack.pop();
            }
            cur = cur.right;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
