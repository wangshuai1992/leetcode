package code;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-10 10:00
 */
public class InsertIntoABinarySearchTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.val > val) {
                if(node.left == null) {
                    node.left = new TreeNode(val);
                    break;
                }
                stack.push(node.left);
            }
            if(node.val < val) {
                if(node.right == null) {
                    node.right = new TreeNode(val);
                    break;
                }
                stack.push(node.right);
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
