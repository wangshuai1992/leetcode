package code;

import java.util.*;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-11 10:20
 */
public class DiameterOfBinaryTree {

    private Map<TreeNode, List<Integer>> cache = new HashMap<>();

    /**
     * preorder traversal, and calculate the max diameter that goes through every node( by add left max down path and
     * right max down path )
     *
     * use cache to avoid overlapping calculate
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int max = 0;
        if(root == null) {
            return max;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            List<Integer> list = calPath(node);
            max = Math.max(max, list.get(0) + list.get(1));
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return max;
    }

    private List<Integer> calPath(TreeNode root) {
        if(cache.containsKey(root)) {
            return cache.get(root);
        }

        int[] arr = new int[2];
        if(root.left != null) {
            List<Integer> left = calPath(root.left);
            arr[0] = 1 + Math.max(left.get(0), left.get(1));
        }
        if(root.right != null) {
            List<Integer> right = calPath(root.right);
            arr[1] = 1 + Math.max(right.get(0), right.get(1));
        }
        List<Integer> res = Arrays.asList(arr[0], arr[1]);
        cache.put(root, res);
        return res;
    }

    int maxNodes;

    /**
     * DFS
     *
     * calculate the node num n of the longest path that goes through current node, then the path length will be n - 1
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree1(TreeNode root) {
        maxNodes = 1;
        depth(root);
        return maxNodes - 1;
    }

    /**
     *
     * @param node
     * @return node num of longest down path(one way)
     */
    private int depth(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftNodes = depth(node.left);
        int rightNodes = depth(node.right);

        //update maxNodes
        maxNodes = Math.max(maxNodes, leftNodes + rightNodes + 1);
        return Math.max(leftNodes, rightNodes) + 1;
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
