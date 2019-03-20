package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-18 10:57
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C%2B%2BJavaPython-One-Pass-Real-O(N)
     * <p>
     * Iterative / One Pass, O(N) Time, O(N) Space
     * <p>
     * 1. Loop on pre array and construct node one by one.
     * 2. stack save the current path of tree.
     * 3. node = new TreeNode(pre[i]), if not left child, add node to the left. otherwise add it to the right.
     * 4. If we meet a same value in the pre and post, it means we complete the construction for current subtree.
     * We pop it from stack.
     *
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Deque<TreeNode> s = new ArrayDeque<>();
        s.offer(new TreeNode(pre[0]));
        for (int i = 1, j = 0; i < pre.length; ++i) {
            TreeNode node = new TreeNode(pre[i]);
            while (s.getLast().val == post[j]) {
                s.pollLast();
                j++;
            }
            if (s.getLast().left == null) {
                s.getLast().left = node;
            } else {
                s.getLast().right = node;
            }
            s.offer(node);
        }
        return s.getFirst();
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161372/Logical-Thinking-with-Code-Beats-99.89
     * <p>
     * Recursive / Divide and Conquer
     *
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        return constructFromPrePost(pre, 0, pre.length - 1, post, 0, pre.length - 1);
    }

    private TreeNode constructFromPrePost(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        // Base cases.
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }

        // Build root.
        TreeNode root = new TreeNode(pre[preStart]);

        // Locate left subtree.
        int leftSubRootInPre = preStart + 1;
        int leftSubRootInPost = findLeftSubRootInPost(pre[leftSubRootInPre], post, postStart, postEnd);
        int leftSubEndInPre = leftSubRootInPre + (leftSubRootInPost - postStart);

        // Divide.
        TreeNode leftSubRoot = constructFromPrePost(pre, leftSubRootInPre, leftSubEndInPre,
                post, postStart, leftSubRootInPost);
        TreeNode rightSubRoot = constructFromPrePost(pre, leftSubEndInPre + 1, preEnd,
                post, leftSubRootInPost + 1, postEnd - 1);

        // Conquer.
        root.left = leftSubRoot;
        root.right = rightSubRoot;
        return root;
    }

    private int findLeftSubRootInPost(int leftSubRootVal, int[] post, int postStart, int postEnd) {
        for (int i = postStart; i <= postEnd; i++) {
            if (post[i] == leftSubRootVal) {
                return i;
            }
        }
        throw new IllegalArgumentException();
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
