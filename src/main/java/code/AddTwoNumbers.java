package code;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-09 10:22
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // dummy head
        ListNode head = new ListNode(0);
        ListNode pre = head;
        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val = 0;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            val += carry;
            ListNode node = new ListNode(val % 10);
            pre.next = node;
            pre = node;
            carry = val / 10;
        }
        if (carry != 0) {
            pre.next = new ListNode(carry);
        }
        return head.next;
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
