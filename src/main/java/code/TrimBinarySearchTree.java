package code;

/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 15:38
 */
public class TrimBinarySearchTree {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) {
            return null;
        }
        if(root.val < L) {
            return trimBST(root.right, L, R);
        }
        if(root.val > R) {
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
