package code;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-11 14:58
 */
public class NaryTreePreorderTraversal {

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        result.add(root.val);

        if(null != root.children && !root.children.isEmpty()) {
            for(Node child : root.children) {
                result.addAll(preorder(child));
            }
        }
        return result;
    }

    public List<Integer> preorder1(Node root) {
        List<Integer> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            if(null != node.children && !node.children.isEmpty()) {
                for(int i=node.children.size() - 1; i>=0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }
        return result;
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
