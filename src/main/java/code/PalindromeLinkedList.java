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

    // 左侧指针
    private ListNode leftGlobal;

    /**
     * 递归 O(n) time, O(n) space（使用栈深度）
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        leftGlobal = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == leftGlobal.val);
        leftGlobal = leftGlobal.next;
        return res;
    }

    /**
     * O(n) time, O(1) space
     *
     * reverse half of the list and compare
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
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
     * O(n) time, O(1) space / 用快慢指针找到链表后半段的开始节点，反转后半段节点并逐一与前半段比较
     *
     * @param head
     * @return
     */
    public boolean isPalindrome3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 如果fast指针没有指向null, 说明链表长度为奇数, 此时slow在最中间节点, slow还要再前进一步
        if (fast != null) {
            slow = slow.next;
        }

        ListNode left = head;
        // 反转后半部分链表
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
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
