package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-14 14:45
 */
public class SmallestSubtreeWithAllTheDeepestNodes {

    private Map<Integer, Integer> cache = new HashMap<>();

    /**
     * 从根节点开始，如果左子树高度等于右子树高度，则当前节点为结果；否则，哪一边高度高，结果就在哪一侧的子树中，以此类推
     *
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        getTreeHeight(root);

        TreeNode cur = root;
        int leftDepth;
        int rightDepth;
        while (cur != null) {
            leftDepth = cur.left == null ? 0 : cache.get(cur.left.val);
            rightDepth = cur.right == null ? 0 : cache.get(cur.right.val);
            if (leftDepth == rightDepth) {
                return cur;
            }
            if (leftDepth > rightDepth) {
                cur = cur.left;
            }
            if (leftDepth < rightDepth) {
                cur = cur.right;
            }
        }
        return null;
    }

    private int getTreeHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            cache.put(root.val, 1);
            return 1;
        }
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);
        int res = 1 + Math.max(leftHeight, rightHeight);
        cache.put(root.val, res);
        return res;
    }

    /**
     * Recursive / one pass
     *
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest1(TreeNode root) {
        return dfs(root).node;
    }

    /**
     * Return the result of the subtree at this node.
     *
     * @param node
     * @return
     */
    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }
        Result leftResult = dfs(node.left);
        Result rightResult = dfs(node.right);
        if (leftResult.dist > rightResult.dist) {
            return new Result(leftResult.node, leftResult.dist + 1);
        }
        if (leftResult.dist < rightResult.dist) {
            return new Result(rightResult.node, rightResult.dist + 1);
        }
        return new Result(node, leftResult.dist + 1);
    }

    /**
     * The result of a subtree is:
     *      Result.node:
     *          the largest depth node that is equal to or an ancestor of all the deepest nodes of this subtree.
     *
     *      Result.dist:
     *          the number of nodes in the path from the root of this subtree, to the deepest node in this subtree.
     */
    private static class Result {
        TreeNode node;
        int dist;

        Result(TreeNode n, int d) {
            node = n;
            dist = d;
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


