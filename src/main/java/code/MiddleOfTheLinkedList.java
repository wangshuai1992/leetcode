package code;

/**
 * https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-10 16:59
 */
public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode node = head;
        while(node != null) {
            size ++;
            node = node.next;
        }

        int index = size / 2;
        node = head;
        int tempIndex = 0;
        while(tempIndex < index) {
            node = node.next;
            tempIndex ++;
        }
        return node;
    }

    public ListNode middleNode1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
