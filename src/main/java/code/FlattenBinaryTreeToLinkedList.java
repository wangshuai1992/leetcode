package code;

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
        if(root == null) {
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
        if(root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        if(left == null && right == null) {
            return root;
        }
        root.left = null;

        TreeNode leftEnd = recurse(left);
        TreeNode rightEnd = recurse(right);

        if(left != null) {
            root.right = left;
            leftEnd.right = right;
        }
        if(right == null) {
            return leftEnd;
        }
        return rightEnd;
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
