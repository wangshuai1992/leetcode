package code;

import java.util.*;

/**
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-11 10:49
 */
public class AllElementsInTwoBinarySearchTrees {

    /**
     * O(m + n)
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        TreeNode cur1 = root1;
        TreeNode cur2 = root2;
        while ((cur1 != null || !stack1.isEmpty()) || (cur2 != null || !stack2.isEmpty())) {
            while (cur1 != null) {
                stack1.push(cur1);
                cur1 = cur1.left;
            }
            while (cur2 != null) {
                stack2.push(cur2);
                cur2 = cur2.left;
            }
            if (stack1.isEmpty()) {
                // tree1 over
                cur2 = stack2.pop();
                result.add(cur2.val);
                cur2 = cur2.right;
                continue;
            }
            if (stack2.isEmpty()) {
                // tree2 over
                cur1 = stack1.pop();
                result.add(cur1.val);
                cur1 = cur1.right;
                continue;
            }

            cur1 = stack1.peek();
            cur2 = stack2.peek();
            if (cur1.val < cur2.val) {
                // take node of tree1
                result.add(cur1.val);
                cur1 = cur1.right;
                stack1.pop();
                // recover pointer of tree2
                cur2 = null;
            } else {
                result.add(cur2.val);
                cur2 = cur2.right;
                stack2.pop();
                cur1 = null;
            }
        }
        return result;
    }

    private List<Integer> res = new ArrayList<>();

    /**
     * recursive
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {
        Queue<Integer> queue = new ArrayDeque<>();
        inOrder(root1, queue, true);
        inOrder(root2, queue, false);
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    private void inOrder(TreeNode root, Queue<Integer> queue, boolean pushEnabled) {
        if (root == null) {
            return;
        }
        inOrder(root.left, queue, pushEnabled);
        if (pushEnabled) {
            // first inorder for tree1
            queue.offer(root.val);
        } else {
            while (!queue.isEmpty() && queue.peek() <= root.val) {
                res.add(queue.poll());
            }
            res.add(root.val);
        }
        inOrder(root.right, queue, pushEnabled);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

}
