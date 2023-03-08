package code;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2022-08-11 19:01
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode begin;
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        begin = dummyhead;
        int i = 0;
        ListNode cur = head;
        while (cur != null) {
            i++;
            if (i % k == 0) {
                // 暂存即将成为下一个begin的节点
                ListNode tempBeginNext = begin.next;
                // 暂存即将成为下一个cur的节点
                ListNode tempCurNext = cur.next;
                reverse(begin, tempCurNext);
                begin = tempBeginNext;
                cur = tempCurNext;
            } else {
                cur = cur.next;
            }
        }
        return dummyhead.next;
    }

    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *            end
     */
    public void reverse(ListNode begin, ListNode end) {
        ListNode first = begin.next;
        // reverse
        ListNode curr = begin.next;
        ListNode prev = begin;
        while (curr != end) {
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }
        // 刚刚处理过的最后一个节点prev成为这段链表的头
        begin.next = prev;
        // 处理的第一个节点的next是传入的end
        first.next = end;
    }

    /**
     * recursive
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        ListNode cur = head;
        ListNode resultHead = head;
        int i = 1;
        while (cur != null) {
            if (i == k) {
                ListNode tempPre = null;
                ListNode tempCur = head;
                for (int j = 0; j < k; j++) {
                    ListNode tempNext = tempCur.next;
                    tempCur.next = tempPre;
                    tempPre = tempCur;
                    tempCur = tempNext;
                }
                // head现在已经成为tail
                head.next = reverseKGroup1(tempCur, k);
                // 反转后的head
                resultHead = tempPre;
            }
            i++;
            cur = cur.next;
        }
        return resultHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode result = new ReverseNodesInKGroup().reverseKGroup(head, 2);
        System.out.println(result.val);
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
