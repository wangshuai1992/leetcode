package code;

/**
 * https://leetcode.com/problems/sort-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-15 11:22
 */
public class SortList {

    /**
     * Recursive
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = sortList(head.next);
        ListNode prev = null;
        ListNode cur = newHead;
        while (cur != null) {
            if (head.val <= cur.val) {
                if (prev != null) {
                    prev.next = head;
                } else {
                    newHead = head;
                }
                head.next = cur;
                return newHead;
            }
            prev = cur;
            cur = cur.next;
        }
        //head is tail
        prev.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * merge sort
     *
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return merge(sortList1(head), sortList1(slow));
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        while (head1 != null || head2 != null) {
            if (head2 == null || (head1 != null && head1.val <= head2.val)) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        return newHead.next;
    }

    /**
     * non-recursive / Bottom-to-up  O(nlogn) time, O(1) space
     *
     * https://leetcode.com/problems/sort-list/discuss/46712/Bottom-to-up(not-recurring)-with-o(1)-space-complextity-and-o(nlgn)-time-complextity
     *
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        }
        for (int step = 1; step < n; step <<= 1) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                prev = merge(left, right, prev);
            }
        }
        return dummy.next;
    }

    private ListNode split(ListNode head, int step) {
        if (head == null) {
            return null;
        }
        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null;
        return right;
    }

    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode cur = prev;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if (left != null) {
            cur.next = left;
        } else if (right != null) {
            cur.next = right;
        }
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
