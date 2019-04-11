package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-11 15:00
 */
public class PalindromeLinkedList {

    /**
     * O(n) time, O(n) space
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int i = 0;
        int j = list.size() - 1;
        while (i <= j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * O(n) time, O(1) space
     *
     * reverse half of the list and compare
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        int mid = (size + 1) >> 1;
        ListNode midNode = head;
        for (int i = 0; i < mid; i++) {
            midNode = midNode.next;
        }
        midNode = reverse(midNode);

        node = midNode;
        ListNode comp = head;
        while (node != null) {
            if (comp.val != node.val) {
                return false;
            }
            comp = comp.next;
            node = node.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    /**
     * O(n) time, O(1) space
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        ListNode reverse = new ListNode(-1);
        reverse.next = head;
        ListNode last = null;
        ListNode fast = reverse;
        ListNode slow;
        ListNode next = reverse.next;

        // reverse the front half linklist
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = next;
            next = slow.next;
            if (last == null) {
                last = slow;
            } else {
                last.next = slow.next;
                slow.next = reverse.next;
                reverse.next = slow;
            }
        }
        slow = reverse.next;
        if (fast == null) {
            slow = slow.next;
        }
        fast = next;
        while (fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(new PalindromeLinkedList().isPalindrome1(head));
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
