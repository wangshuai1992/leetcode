package code;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-17 17:07
 */
public class BinaryTreePreorderTraversal {

    /**
     * Recursive
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        preOrder(root, ans);
        return ans;
    }

    private void preOrder(TreeNode root, List<Integer> ans) {
        if(null == root) {
            return;
        }
        ans.add(root.val);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }

    /**
     * Iterative
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return ans;
    }

    /**
     * Morris 遍历 / O(n) time, O(1) space
     *
     * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其前序遍历规则总结如下：
     *
     * 1. 新建临时节点，令该节点为 root；
     * 2. 如果当前节点的左子节点为空，将当前节点加入答案，并遍历当前节点的右子节点；
     * 3. 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点：
     *      如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点。然后将当前节点加入答案，并将前驱节点的右子节点更新为当前节点。当前节点更新为当前节点的左子节点。
     *      如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。当前节点更新为当前节点的右子节点。
     * 4. 重复步骤 2 和步骤 3，直到遍历结束。
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode p1 = root;
        TreeNode p2;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
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
