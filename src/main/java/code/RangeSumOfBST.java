package code;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/range-sum-of-bst/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-12 15:06
 */
public class RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int left, int right) {
        int sum = 0;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.val >= left && node.val <= right) {
                sum += node.val;
            }
            //当node.val <= left时， 左子树的值都小于left，此时左子树不需要遍历
            if(node.val > left && node.left != null) {
                stack.push(node.left);
            }
            //当node.val >= right时， 右子树的值都大于right，此时右子树不需要遍历
            if(node.val < right && node.right != null) {
                stack.push(node.right);
            }
        }
        return sum;
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
