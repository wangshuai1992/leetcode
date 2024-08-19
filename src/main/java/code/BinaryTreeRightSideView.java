package code;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2021-04-12 16:22
 */
public class BinaryTreeRightSideView {

    /**
     * level order traversal
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }

    /**
     * dfs
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int maxDepth = -1;
        Deque<TreeNode> nodeStack = new ArrayDeque<>();
        Deque<Integer> depthStack = new ArrayDeque<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            if (node != null) {
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth, depth);
                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }
        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }
        return rightView;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
