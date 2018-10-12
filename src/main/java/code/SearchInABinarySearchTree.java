package code;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-11 11:06
 */
public class SearchInABinarySearchTree {

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return null;
        }
        if(root.val == val) {
            return root;
        }
        if(root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    public TreeNode searchBST1(TreeNode root, int val) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(null == node) {
                return null;
            }
            int nodeVal = node.val;
            if(nodeVal == val) {
                return node;
            }
            if(nodeVal > val) {
                stack.push(node.left);
            }
            if(nodeVal < val) {
                stack.push(node.right);
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
    }

}
