package code;

import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-10 10:41
 */
public class LowestCommonAncestorOfABinaryTree {

    /**
     * Judge if p and q are in one side of a sub tree from top to bottom
     *
     * O(nlogn) time, O(n) space
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        int pIndex = list.indexOf(p);
        int qIndex = list.indexOf(q);

        TreeNode ancestor = root;
        int ancIndex = list.indexOf(ancestor);
        //same side
        while ((pIndex - ancIndex) * (qIndex - ancIndex) > 0) {
            ancestor = (pIndex - ancIndex) < 0 ? ancestor.left : ancestor.right;
            ancIndex = list.indexOf(ancestor);
        }
        return ancestor;
    }

    private void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }

    /**
     * 后续遍历
     * https://www.cnblogs.com/labuladong/p/13976582.html
     * 扩展定义 (题目定义公共祖先必定存在, 递归的最外层肯定是第一种情况)
     *   1. 如果 p 和 q 都在以 root 为根的树中, 函数返回的就是 p 和 q 的最近公共祖先节点
     *   2. 那如果 p 和 q 都不在以 root 为根的树中, 返回null
     *   3. 如果 p 和 q 只有一个存在于 root 为根的树中, 则返回这个存在的节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        // 情况 1. 如果 p 和 q 都在以 root 为根的树中，那么 left 和 right 一定分别是 p 和 q（从 base case 看出来的）。
        if (left != null && right != null) {
            // 由于是后续遍历 是从下往上的 所以这里第一次相交的就是最近祖先
            return root;
        }
        // 情况 2. 如果 p 和 q 都不在以 root 为根的树中，直接返回 null。
        if (left == null && right == null) {
            return null;
        }
        // 情况 3. 如果 p 和 q 只有一个存在于 root 为根的树中，函数返回该节点。
        return left == null ? right : left;
    }

    private TreeNode ans;

    /**
     * Recursive / O(N) time, O(N) space
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        recurseTree(root, p, q);
        return ans;
    }

    /**
     * return true if p OR q is in the tree
     */
    private boolean recurseTree(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        int mid = (node == p) || (node == q) ? 1 : 0;

        int left = recurseTree(node.left, p, q) ? 1 : 0;

        int right = recurseTree(node.right, p, q) ? 1 : 0;

        // If any two of the flags left, right or mid become True
        if (left + right + mid >= 2) {
            ans = node;
        }
        // Return true if any one of the three bool values is True.
        return left + right + mid > 0;
    }

    /**
     * Iterative using parent pointers
     *
     * O(N) time, O(N) space
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();
        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        parentMap.put(root, null);
        stack.push(root);

        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> pParents = new HashSet<>();
        while (p != null) {
            pParents.add(p);
            p = parentMap.get(p);
        }

        while (!pParents.contains(q)) {
            q = parentMap.get(q);
        }
        return q;
    }


    /**
     * Three static flags to keep track of post-order traversal.
     *
     * Both left and right traversal pending for a node.
     * Indicates the nodes children are yet to be traversed.
     */
    private static int BOTH_PENDING = 2;

    /**
     * Left traversal done.
     */
    private static int LEFT_DONE = 1;

    /**
     * Both left and right traversal done for a node.
     * Indicates the node can be popped off the stack.
     */
    private static int BOTH_DONE = 0;

    /**
     * Iterative without parent pointers
     *
     * O(N) time, O(N) space
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        // Initialize the stack with the root node.
        stack.push(new Pair<TreeNode, Integer>(root, BOTH_PENDING));
        // This flag is set when either one of p or q is found.
        boolean oneNodeFound = false;
        // This is used to keep track of the LCA.
        TreeNode lca = null;
        // Child node
        TreeNode childNode = null;

        // We do a post order traversal of the binary tree using stack
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parentNode = top.getKey();
            int parentState = top.getValue();

            // If the parentState is not equal to BOTH_DONE,
            // this means the parent_node can't be popped off yet.
            if (parentState != BOTH_DONE) {
                // If both child traversals are pending
                if (parentState == BOTH_PENDING) {
                    // Check if the current parent_node is either p or q.
                    if (parentNode == p || parentNode == q) {
                        // If oneNodeFound was set already, this means we have found
                        // both the nodes.
                        if (oneNodeFound) {
                            return lca;
                        } else {
                            // Otherwise, set oneNodeFound to True,
                            // to mark one of p and q is found.
                            oneNodeFound = true;

                            // Save the current top element of stack as the LCA.
                            lca = stack.peek().getKey();
                        }
                    }
                    // If both pending, traverse the left child first
                    childNode = parentNode.left;
                } else {
                    // traverse right child
                    childNode = parentNode.right;
                }

                // Update the node state at the top of the stack
                // Since we have visited one more child.
                stack.pop();
                stack.push(new Pair<TreeNode, Integer>(parentNode, parentState - 1));

                // Add the child node to the stack for traversal.
                if (childNode != null) {
                    stack.push(new Pair<TreeNode, Integer>(childNode, BOTH_PENDING));
                }
            } else {
                // If the parentState of the node is both done,
                // the top node could be popped off the stack.
                // Update the LCA node to be the next top node.
                if (lca == stack.pop().getKey() && oneNodeFound) {
                    lca = stack.peek().getKey();
                }
            }
        }
        return null;
    }

    /**
     * Definition for a binary tree node.
     */
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
