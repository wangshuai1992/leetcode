package code;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-04-29 14:12
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {

    /**
     * recursive
     *
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || cloned == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode node = getTargetCopy(original.left, cloned.left, target);
        if (node != null) {
            return node;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }

    /**
     * Level Order Traversal
     *
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(original);
        int nodeIndex = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == target) {
                    queue.clear();
                    break;
                }
                if (node != null && node.left != null) {
                    queue.offer(node.left);
                }
                if (node != null && node.right != null) {
                    queue.offer(node.right);
                }
                nodeIndex ++;
            }
        }

        queue = new ArrayDeque<>();
        queue.offer(cloned);
        int clonedIndex = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (clonedIndex == nodeIndex) {
                    return node;
                }
                if (node != null && node.left != null) {
                    queue.offer(node.left);
                }
                if (node != null && node.right != null) {
                    queue.offer(node.right);
                }
                clonedIndex ++;
            }
        }
        return null;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

}
