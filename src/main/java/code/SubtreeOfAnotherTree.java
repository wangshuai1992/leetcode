package code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-29 16:03
 */
public class SubtreeOfAnotherTree {

    /**
     * Using String to represent tree
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree1 = preorder(s, true);
        String tree2 = preorder(t, true);
        return tree1.contains(tree2);
    }

    private String preorder(TreeNode t, boolean left) {
        if (t == null) {
            return left ? "lnull" : "rnull";
        }
        return "#" + t.val + " " + preorder(t.left, true) + " " + preorder(t.right, false);
    }

    /**
     * By Comparison of Nodes
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        if(s == null) {
            return false;
        }
        return isEqualTree(s, t) || isSubtree1(s.left, t) || isSubtree1(s.right, t);
    }

    /**
     * Recursive
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isEqualTree(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val && isEqualTree(a.left, b.left) && isEqualTree(a.right, b.right);
    }

    /**
     * Iterative
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isEqualTree1(TreeNode a, TreeNode b) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(a);
        queue.offer(b);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }
        return true;
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
