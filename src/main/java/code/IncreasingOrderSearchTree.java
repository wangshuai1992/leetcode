package code;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/increasing-order-search-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-16 16:13
 */
public class IncreasingOrderSearchTree {

    /**
     * DFS / IN-ORDER traversal
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        if(root == null) {
            return null;
        }

        List<Integer> vals = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            //current为空时 不搜索左子节点 直接从栈中取 避免左节点循环遍历
            current = stack.pop();
            vals.add(current.val);
            current = current.right;
        }

        TreeNode newRoot = new TreeNode(0);
        TreeNode temp = newRoot;
        for(int i : vals) {
            temp.right = new TreeNode(i);
            temp = temp.right;
        }
        return newRoot.right;
    }

    /**
     * DFS / IN-ORDER traversal with relinking
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST1(TreeNode root) {
        if(root == null) {
            return null;
        }

        //creating dummy root
        TreeNode dummyRoot = new TreeNode(0);
        //tail of rearranged tree
        TreeNode tail = dummyRoot;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            //visit node - link it to the tail and cut its left child
            tail.right = current;
            tail = tail.right;
            current.left = null;

            current = current.right;
        }

        return dummyRoot.right;
    }

    /**
     * Recursive / in-order
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inOrder1(root, ans);

        //creating dummy root
        TreeNode dummyRoot = new TreeNode(0);
        TreeNode temp = dummyRoot;
        for(int i : ans) {
            temp.right = new TreeNode(i);
            temp = temp.right;
        }
        return dummyRoot.right;
    }

    private void inOrder1(TreeNode node, List<Integer> ans) {
        if(null == node) {
            return;
        }
        inOrder1(node.left, ans);
        ans.add(node.val);
        inOrder1(node.right, ans);
    }

    /**
     * Recursive / Traversal with Relinking
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST3(TreeNode root) {
        return inOrder2(root, null);
    }

    /**
     *
     * @param root root of the tree that needs to be rearranged
     * @param tail the part that needs to be add to the tail of the rearranged tree
     * @return new root of the rearranged tree
     */
    private TreeNode inOrder2(TreeNode root, TreeNode tail) {
        if(root == null) {
            return tail;
        }
        TreeNode newRoot = inOrder2(root.left, root);
        root.left = null;
        root.right = inOrder2(root.right, tail);
        return newRoot;
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
