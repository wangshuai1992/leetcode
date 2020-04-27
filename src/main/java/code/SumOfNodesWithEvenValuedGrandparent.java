package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-15 17:02
 */
public class SumOfNodesWithEvenValuedGrandparent {

    private int sum;

    public int sumEvenGrandparent(TreeNode root) {
        preOrder(root, new HashSet<>());
        return sum;
    }

    private void preOrder(TreeNode root, Set<TreeNode> hasVisitSet) {
        if (root == null) {
            return;
        }
        boolean isEven = (root.val & 1) == 0;
        if (isEven && root.left != null) {
            if (root.left.left != null && !hasVisitSet.contains(root.left.left)) {
                hasVisitSet.add(root.left.left);
                sum += root.left.left.val;
            }
            if(root.left.right != null && !hasVisitSet.contains(root.left.right)) {
                hasVisitSet.add(root.left.right);
                sum += root.left.right.val;
            }
        }
        if (isEven && root.right != null) {
            if (root.right.left != null && !hasVisitSet.contains(root.right.left)) {
                hasVisitSet.add(root.right.left);
                sum += root.right.left.val;
            }
            if(root.right.right != null && !hasVisitSet.contains(root.right.right)) {
                hasVisitSet.add(root.right.right);
                sum += root.right.right.val;
            }
        }
        preOrder(root.left, hasVisitSet);
        preOrder(root.right, hasVisitSet);
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
