package code;

import java.util.*;

/**
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-18 10:31
 */
public class NaryTreeLevelOrderTraversal {

    /**
     * Iterative / BFS
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int levelSize = queue.size();
            for(int i=0; i<levelSize; i++) {
                Node node = queue.poll();
                if(node == null) {
                    continue;
                }
                for(Node n : node.children) {
                    queue.offer(n);
                }
                level.add(node.val);
            }
            if(!level.isEmpty()) {
                res.add(level);
            }
        }
        return res;
    }

    /**
     * Recursive / BFS
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }
        recursiveBFS(Collections.singletonList(root), levels);
        return levels;
    }

    private void recursiveBFS(List<Node> nodes, List<List<Integer>> levels) {
        if (nodes.isEmpty()) {
            return;
        }
        List<Integer> level = new ArrayList<>();
        List<Node> nextNodes = new ArrayList<>();
        for (Node node : nodes) {
            level.add(node.val);
            nextNodes.addAll(node.children);
        }
        levels.add(level);
        recursiveBFS(nextNodes, levels);
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {

        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

}
