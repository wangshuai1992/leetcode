/**
 * https://leetcode.com/problems/binary-tree-pruning/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 14:48
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        if(root.left != null) {
            root.left = pruneTree(root.left);
        }

        if(root.right != null) {
            root.right = pruneTree(root.right);
        }

        if(root.left == null && root.right == null && root.val == 0) {
            return null;
        }

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
