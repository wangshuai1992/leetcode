package code;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/invert-binary-tree/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-02 17:00
 */
public class InvertBinaryTree {

    public TreeNode invertTree1(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = invertTree1(root.right);
        root.right = invertTree1(temp);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if(root == null) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
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
