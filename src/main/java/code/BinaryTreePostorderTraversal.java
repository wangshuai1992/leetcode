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

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(null == node) {
                continue;
            }
            ans.addFirst(node.val);
            stack.push(node.left);
            stack.push(node.right);
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
     * Iterative / Using a pre pointer to record the last visted node
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            // pre == cur.right 表示对某个右子树访问完成
            if(cur.right == null || pre == cur.right){
                res.add(cur.val);
                pre = cur;
                cur = null;
            } else {
                //这里不是while循环
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
    }

}
