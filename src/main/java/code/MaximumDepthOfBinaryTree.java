package code;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-20 10:34
 */
public class MaximumDepthOfBinaryTree {

    /* BFS
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.remove();
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return level;
    }
    */

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
