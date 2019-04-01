package code;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-20 18:00
 */
public class SerializeAndDeserializeBinaryTree {

    private static final String spliter = ",";
    private static final String NN = "X";

    /**
     * Encodes a tree to a single string.
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        buildString(root, builder);
        return builder.toString();
    }

    private void buildString(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append(NN).append(spliter);
        } else {
            builder.append(root.val).append(spliter);
            buildString(root.left, builder);
            buildString(root.right, builder);
        }
    }

    /**
     * Decodes your encoded data to tree.
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        String[] arr = data.split(spliter);
        Queue<String> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(arr));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String s = queue.poll();
        if(s == null || NN.equals(s)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(s));
            node.left = buildTree(queue);
            node.right = buildTree(queue);
            return node;
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
