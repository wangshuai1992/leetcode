package code;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-06 09:25
 */
public class ConvertBSTToGreaterTree {

    /**
     * reverse inorder-traversal
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }

            cur = stack.pop();
            if(pre != null) {
                cur.val = cur.val + pre.val;
            }
            pre = cur;
            cur = cur.left;
        }
        return root;
    }

    private int sum = 0;

    /**
     * Recursive
     *
     * @param root
     * @return
     */
    public TreeNode convertBST1(TreeNode root) {
        if (root != null) {
            convertBST1(root.right);
            sum += root.val;
            root.val = sum;
            convertBST1(root.left);
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

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

}
