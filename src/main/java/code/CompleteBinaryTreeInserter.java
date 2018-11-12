package code;


import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/complete-binary-tree-inserter/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-08 16:33
 */
public class CompleteBinaryTreeInserter {

    private class CBTInserter {

        private TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;
        }

        public int insert(int v) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if(node.left == null) {
                        node.left = new TreeNode(v);
                        return node.val;
                    } else {
                        queue.add(node.left);
                    }
                    if(node.right == null) {
                        node.right = new TreeNode(v);
                        return node.val;
                    } else {
                        queue.add(node.right);
                    }
                }
            }
            return 0;
        }

        public TreeNode get_root() {
            return root;
        }
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
