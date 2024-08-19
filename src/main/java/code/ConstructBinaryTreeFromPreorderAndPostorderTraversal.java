package code;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(new TreeNode(pre[0]));
        for (int i = 1, j = 0; i < pre.length; ++i) {
            TreeNode node = new TreeNode(pre[i]);
            while (deque.getLast().val == post[j]) {
                deque.pollLast();
                j++;
            }
            if (deque.getLast().left == null) {
                deque.getLast().left = node;
            } else {
                deque.getLast().right = node;
            }
            deque.addLast(node);
        }
        return deque.getFirst();
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161372/Logical-Thinking-with-Code-Beats-99.89
     * <p>
     * Recursive
     *
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        return constructFromPrePost(pre, 0, pre.length - 1, post, 0, pre.length - 1);
    }

    private TreeNode constructFromPrePost(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        }

        // Locate left subtree.
        int leftSubRootInPre = preStart + 1;
        int leftSubRootInPost = findLeftSubRootInPost(pre[leftSubRootInPre], post, postStart, postEnd);
        int leftSubEndInPre = leftSubRootInPre + (leftSubRootInPost - postStart);

        root.left = constructFromPrePost(pre, leftSubRootInPre, leftSubEndInPre, post, postStart, leftSubRootInPost);
        root.right = constructFromPrePost(pre, leftSubEndInPre + 1, preEnd, post, leftSubRootInPost + 1, postEnd - 1);
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

    private Map<Integer, Integer> postIndexMap = new HashMap<>();
    private Map<Integer, Integer> preIndexMap = new HashMap<>();

    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        for (int i = 0; i < pre.length; i++) {
            preIndexMap.put(pre[i], i);
            postIndexMap.put(post[i], i);
        }
        return buildTree(0, pre.length - 1, pre, post);
    }

    private TreeNode buildTree(int start, int end, int[] pre, int[] post) {
        if (start > end) {
            return null;
        }
        int rootVal = pre[start];
        if (start == end) {
            return new TreeNode(rootVal);
        }
        TreeNode root = new TreeNode(pre[start]);
        int rightTreeStartIndex = preIndexMap.get(post[postIndexMap.get(rootVal) - 1]);
        root.left = buildTree(start + 1, rightTreeStartIndex - 1, pre, post);
        root.right = buildTree(rightTreeStartIndex, end, pre, post);
        return root;
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
