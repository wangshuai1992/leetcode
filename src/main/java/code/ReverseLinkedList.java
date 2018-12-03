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
        //head.next此时已经在新链表末尾
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

    /**
     * iterative
     *
     * 从第三个节点开始，不断将节点取出，插入到当前第一个和第二个节点之间，最终将头结点移到末尾
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        //一开始的第二个节点（循环过程中不断后移）
        ListNode headNext = head.next;

        //每次需要移动的节点
        ListNode moving;

        while (headNext.next != null) {
            moving = headNext.next;
            headNext.next = moving.next;
            moving.next = head.next;
            head.next = moving;
        }

        headNext.next = head;
        //此时的head已经在链表尾部
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
