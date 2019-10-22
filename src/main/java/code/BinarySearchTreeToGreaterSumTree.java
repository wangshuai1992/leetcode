package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-08-07 17:27
 */
public class BinarySearchTreeToGreaterSumTree {

    private int sum;

    /**
     * Iterative / reverse inorder traversal
     *
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        sum = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }

            cur = stack.pop();
            cur.val = sum + cur.val;
            sum = cur.val;
            cur = cur.left;
        }
        return root;
    }

    /**
     * recursive
     *
     * @param root
     * @return
     */
    public TreeNode bstToGst1(TreeNode root) {
        sum = 0;
        recurse(root);
        return root;
    }

    /**
     * return sum of nodes greater than root, and set root value
     *
     * @param root
     * @return
     */
    private void recurse(TreeNode root) {
        if(root == null) {
            return;
        }
        recurse(root.right);
        root.val = sum + root.val;
        sum = root.val;
        recurse(root.left);
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
