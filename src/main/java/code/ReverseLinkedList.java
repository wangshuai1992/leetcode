package code;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-26 10:21
 */
public class ReverseLinkedList {

    /**
     * recursive
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * iterative
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
