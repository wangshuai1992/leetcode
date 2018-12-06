package code;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/flip-equivalent-binary-trees/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-03 14:10
 */
public class FlipEquivalentBinaryTrees {

    /**
     * Recursive
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null) {
            return root1 == root2;
        }

        if(root1.left == null && root1.right == null && root2.left == null && root2.right == null) {
            return root1.val == root2.val;
        }

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right));
    }

    /**
     * Canonical Traversal
     *
     * Flip each node so that the left child is smaller than the right, and call this the canonical representation.
     * All equivalent trees have exactly one canonical representation.
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv1(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        dfs(root2, list2);
        return list1.equals(list2);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            int leftVal = root.left != null ? root.left.val : -1;
            int rightVal = root.right != null ? root.right.val : -1;

            if (leftVal < rightVal) {
                dfs(root.left, list);
                dfs(root.right, list);
            } else {
                dfs(root.right, list);
                dfs(root.left, list);
            }
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
