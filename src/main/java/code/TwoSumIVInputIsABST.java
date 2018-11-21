package code;


import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-21 11:06
 */
public class TwoSumIVInputIsABST {

    /**
     * in-order traversal and search / brute force
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        //in-order
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if(cur.val * 2 >= k) {
                return false;
            }
            if(search(root, k - cur.val)) {
                return true;
            }
            cur = cur.right;
        }
        return false;
    }

    private boolean search(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        if(root.val == k) {
            return true;
        }
        if(root.val > k) {
            return search(root.left, k);
        }
        return search(root.right, k);
    }

    /**
     * traversal and hashset
     *
     * use hashset to store values, so that search takes O(1) time
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget1(TreeNode root, int k) {
        //pre-order
        Set<Integer> values = new HashSet<>();

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(values.contains(k - node.val)) {
                return true;
            }
            values.add(node.val);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return false;
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
