package code;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-11 15:13
 */
public class MaximumDepthOfNaryTree {

    /**
     * recursion
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (null == root) {
            return 0;
        }
        int maxChildDepth = 0;
        for (Node child : root.children) {
            maxChildDepth = Math.max(maxChildDepth, maxDepth(child));
        }
        return ++maxChildDepth;
    }

    /**
     * BFS
     *
     * @param root
     * @return
     */
    public int maxDepth1(Node root) {
        if(null == root) {
            return 0;
        }
        int result = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            result ++;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Node node = queue.poll();
                for(Node child : node.children) {
                    queue.offer(child);
                }
            }
        }
        return result;
    }

    /**
     * DFS
     *
     * @param root
     * @return
     */
    public int maxDepth2(Node root) {
        Deque<Pair<Node, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.push(new Pair<Node, Integer>(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.pop();
            root = current.getKey();
            int currentDepth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                for (Node c : root.children) {
                    stack.add(new Pair<Node, Integer>(c, currentDepth + 1));
                }
            }
        }
        return depth;
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
