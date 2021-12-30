package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2021-04-12 19:44
 */
public class CopyListWithRandomPointer {

    /**
     * O(n) time, O(n) space, using hash map
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> newNodeMap = new HashMap<>();
        Node newHead = new Node(head.val);
        newNodeMap.put(head, newHead);
        Node curOld = head.next;
        Node curNew = newHead;
        while (curOld != null) {
            curNew.next = newNodeMap.get(curOld);
            if (curNew.next == null) {
                curNew.next = new Node(curOld.val);
            }
            newNodeMap.put(curOld, curNew.next);
            curNew = curNew.next;
            curOld = curOld.next;
        }
        // set all random pointer
        curOld = head;
        while (curOld != null) {
            Node newNode = newNodeMap.get(curOld);
            newNode.random = newNodeMap.get(curOld.random);
            curOld = curOld.next;
        }
        return newHead;
    }

    /**
     * O(n) time, O(1) space
     * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
     *
     * @param head
     * @return
     */
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        // copy new node
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        // set random pointer of new node
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // restore old and extract new
        Node dummy = new Node(0);
        cur = head;
        Node newCur = dummy;
        while (cur != null) {
            newCur.next = cur.next;
            newCur = newCur.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
