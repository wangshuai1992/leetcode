package code;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2022-06-21 15:24
 */
public class SwapNodesInPairs {

    /**
     * recursive  O(n) time, O(n) space
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode temp = swapPairs(head.next.next);
        dummy.next = head.next;
        dummy.next.next = head;
        dummy.next.next.next = temp;
        return dummy.next;
    }

    /**
     * iterative  O(n) time, O(1) space
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode resultNode = head.next;
        while (head != null && head.next != null) {
            ListNode tempHead = head.next.next;
            ListNode tempDummy = head;
            dummy.next = head.next;
            dummy.next.next = head;
            dummy.next.next.next = tempHead;
            dummy = tempDummy;
            head = tempHead;
        }
        return resultNode;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(ListNode next) {
            this.next = next;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
