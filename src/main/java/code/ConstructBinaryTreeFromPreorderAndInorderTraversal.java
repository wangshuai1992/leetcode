package code;

import java.util.*;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-19 17:21
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * Recursive
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return recurseBuild(preorder, 0, inorder, 0, inorder.length);
    }

    private TreeNode recurseBuild(int[] preorder, int rootIdxPre, int[] inorder, int from, int to) {
        if (from == to) {
            return null;
        }
        if (from + 1 == to) {
            return new TreeNode(inorder[from]);
        }
        TreeNode root = new TreeNode(preorder[rootIdxPre]);
        int index = -1;
        for (int i = from; i < to; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }
        root.left = recurseBuild(preorder, ++rootIdxPre, inorder, from, index);
        root.right = recurseBuild(preorder, rootIdxPre + (index - from), inorder, index + 1, to);
        return root;
    }

    /**
     * Recursive / use map to reduce search time
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        // for search index in inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recurseBuild(preorder, 0, inorder, 0, inorder.length, map);
    }

    private TreeNode recurseBuild(int[] preorder, int rootIdxPre, int[] inorder, int from, int to, Map<Integer, Integer> map) {
        if (from == to) {
            return null;
        }
        if (from + 1 == to) {
            return new TreeNode(inorder[from]);
        }
        TreeNode root = new TreeNode(preorder[rootIdxPre]);
        int index = map.get(root.val);
        root.left = recurseBuild(preorder, ++rootIdxPre, inorder, from, index, map);
        root.right = recurseBuild(preorder, rootIdxPre + (index - from), inorder, index + 1, to, map);
        return root;
    }

    /**
     * Iterative using stack
     *
     * 1. Keep pushing the nodes from the preorder into a stack (and keep making the tree by adding nodes to the left
     * of the previous node) until the top of the stack matches the inorder.
     *
     * 2. At this point, pop the top of the stack until the top does not equal inorder (keep a flag to note that
     * you have made a pop).
     *
     * 3. Repeat 1 and 2 until preorder is empty. The key point is that whenever the flag is set, insert a node to
     * the right and reset the flag.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode node = root;
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            TreeNode curNode = new TreeNode(preorder[i]);
            boolean isRight = false;
            while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                node = stack.pop();
                isRight = true;
                inorderIndex++;
            }
            if (isRight) {
                node.right = curNode;
            } else {
                node.left = curNode;
            }
            node = curNode;
            stack.push(node);
        }
        return root;
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
