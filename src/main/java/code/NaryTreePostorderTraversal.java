package code;

import java.util.*;

/**
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-11 11:25
 */
public class NaryTreePostorderTraversal {

    public List<Integer> postorder(Node root) {
        List<Integer> list = new LinkedList<>();
        if(root == null) {
            return list;
        }
        for(Node child : root.children) {
            list.addAll(postorder(child));
        }
        list.add(root.val);
        return list;
    }

    /**
     * The code visits the nodes Root->right->left which is the opposite of post order traversal
     *
     * use result.addFirst() to reverse the order
     *
     * @param root
     * @return
     */
    public List<Integer> postorder1(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if(null == node) {
                continue;
            }
            result.addFirst(node.val);
            if(null != node.children && !node.children.isEmpty()) {
                for(Node child : node.children) {
                    stack.push(child);
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
