package code;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-28 02:26
 */
@SuppressWarnings("SimplifiableIfStatement")
public class ValidateBinarySearchTree {

    /**
     * recursive
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    private Integer max;

    /**
     * recursive / inorder traversal
     *
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        return inorderTraversalRecursive(root);
    }

    private boolean inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorderTraversalRecursive(root.left)) {
            return false;
        }
        if (max != null && root.val <= max) {
            return false;
        }
        max = root.val;
        return inorderTraversalRecursive(root.right);
    }

    /**
     * iterative / inorder traversal
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Integer maxValue = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (maxValue != null && cur.val <= maxValue) {
                return false;
            }
            maxValue = cur.val;
            cur = cur.right;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        new ValidateBinarySearchTree().isValidBST(root);
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
