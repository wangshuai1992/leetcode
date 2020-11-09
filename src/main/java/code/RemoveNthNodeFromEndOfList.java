package code;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-25 14:50
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int size = 1;
        ListNode temp = head;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        if (n > size) {
            return null;
        }
        int index = 0;
        int targetIndex = size - n;
        ListNode dummy = new ListNode();
        // dummy node
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (index < targetIndex) {
            pre = cur;
            cur = cur.next;
            index++;
        }
        pre.next = cur.next;
        return dummy.next;
    }

    /**
     * one pass with fast/slow pointers
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        for (int m = 0; m < n; m++) {
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
