package code;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/leaf-similar-trees/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-12 16:27
 */
public class LeafSimilarTrees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return getLeafSeq1(root1).equals(getLeafSeq1(root2));
    }

    private List<Integer> getLeafSeq(TreeNode root) {
        List<Integer> seq = new ArrayList<>();

        if(root == null) {
            return seq;
        }

        //pre-order  dfs
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;

            if(left == null && right == null) {
                seq.add(node.val);
            }

            if(right != null) {
                stack.push(right);
            }
            if(left != null) {
                stack.push(left);
            }
        }
        return seq;
    }

    private List<Integer> getLeafSeq1(TreeNode root) {
        List<Integer> seq = new ArrayList<>();

        if(root == null) {
            return seq;
        }
        if(root.left == null && root.right == null) {
            seq.add(root.val);
        }
        seq.addAll(getLeafSeq1(root.left));
        seq.addAll(getLeafSeq1(root.right));
        return seq;
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
