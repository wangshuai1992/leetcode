package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-10 09:59
 */
public class LinkedListCycleII {

    /**
     * Hash set
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        Set<ListNode> check = new HashSet<>();

        while(head != null){
            if(check.contains(head)){
                return head;
            } else {
                check.add(head);
                head = head.next;
            }
        }
        return null;
    }

    /**
     * two pointer
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if(fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }
        if(fast == null) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
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
