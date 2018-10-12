package code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-16 10:14
 */
public class FindBottomLeftTreeValue {

    /*
    public int findBottomLeftValue(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();

        traverse(root, map, 1);

        int maxLevel = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int level = entry.getKey();
            if(level > maxLevel) {
                maxLevel = level;
            }
        }
        return map.get(maxLevel);
    }

    private void traverse(TreeNode root, Map<Integer,Integer> map, int i) {
        //中序遍历二叉树, 并记录每层最左边元素的值
        if(root.left != null) {
            traverse(root.left, map, i+1);
        }
        map.putIfAbsent(i, root.val);
        if(root.right != null) {
            traverse(root.right, map, i+1);
        }
    }
    */

    /**
     * Doing BFS right-to-left means we can simply return the last node’s value and don’t have to keep track of
     * the first node in the current row or even care about rows at all.
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node = root;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return node.val;
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
