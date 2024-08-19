package code;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-17 17:03
 */
public class BinaryTreePostorderTraversal {

    /**
     * Iterative / root -> right -> left and reverse
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return ans;
    }

    /**
     * Recursive
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        postOrder(root, ans);
        return ans;
    }

    private void postOrder(TreeNode root, List<Integer> ans) {
        if(null == root) {
            return;
        }
        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.val);
    }

    /**
     * Iterative
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        Set<TreeNode> seen = new HashSet<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur == null && seen.contains(stack.peek())) {
                // 访问节点
                ans.add(stack.pop().val);
            } else if (cur == null) {
                // 找右子树
                seen.add(stack.peek());
                cur = stack.peek().right;
            } else {
                // 往下找左子树
                stack.push(cur);
                cur = cur.left;
            }
        }
        return ans;
    }

    /**
     * Iterative / Using a pre pointer to record the last visted node
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                // 一直往下找左子树
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            // pre == cur.right 表示对某个右子树访问完成
            if (cur.right == null || pre == cur.right) {
                // 真正访问节点的操作
                res.add(cur.val);
                // 记录pre
                pre = cur;
                // cur设为null, 进入下一个while循环访问stack里面的数据
                cur = null;
            } else {
                // 这里不是while循环
                stack.push(cur);
                cur = cur.right;
            }
        }
        return res;
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
