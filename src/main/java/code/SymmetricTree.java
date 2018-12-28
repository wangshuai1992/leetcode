package code;

import java.util.*;

/**
 * https://leetcode.com/problems/symmetric-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-26 15:36
 */
public class SymmetricTree {

    /**
     * left -> right -> root should equal to right -> left -> root
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        List<Integer> lmr = new ArrayList<>();
        List<Integer> rml = new ArrayList<>();

        recurse(root, lmr, false);
        recurse(root, rml, true);

        for (int i = 0; i < lmr.size(); i++) {
            Integer i1 = lmr.get(i);
            Integer i2 = rml.get(i);
            if (i1 == null && i2 != null || (i1 != null && i2 == null)) {
                return false;
            }
            if (i1 != null && !i1.equals(i2)) {
                return false;
            }
        }
        return true;
    }

    private void recurse(TreeNode root, List<Integer> list, boolean reverse) {
        if (root == null) {
            list.add(null);
            return;
        }
        if (!reverse) {
            recurse(root.left, list, false);
            recurse(root.right, list, false);
            list.add(root.val);
        } else {
            recurse(root.right, list, true);
            recurse(root.left, list, true);
            list.add(root.val);
        }
    }

    /**
     * Recursive
     *
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    /**
     * Iterative / using queue
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    /**
     * Iterative / using stack
     *
     * @param root
     * @return
     */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            if (node2 == null && node1 == null) {
                continue;
            }
            if (node2 == null || node1 == null || node1.val != node2.val) {
                return false;
            }
            stack.push(node2.left);
            stack.push(node1.right);
            stack.push(node2.right);
            stack.push(node1.left);
        }
        return true;
    }

    /**
     * LevelOrderTraversal and judge if every level is symmetric
     *
     * @param root
     * @return
     */
    public boolean isSymmetric4(TreeNode root) {
        List<Integer> temp = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            temp.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    temp.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    temp.add(null);
                }
            }
            if (!isSymmetricList(temp)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSymmetricList(List<Integer> list) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            Integer i1 = list.get(start);
            Integer i2 = list.get(end);
            if (i1 == null && i2 == null) {
                start++;
                end--;
                continue;
            }
            if (i1 == null || i2 == null) {
                return false;
            }
            if (!i1.equals(i2)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
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
