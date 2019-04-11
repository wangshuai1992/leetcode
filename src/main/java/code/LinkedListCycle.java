package code;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-10 09:51
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if(fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }
        return false;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
