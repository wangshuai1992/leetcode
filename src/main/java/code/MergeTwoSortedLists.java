package code;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-17 10:28
 */
public class MergeTwoSortedLists {

    /**
     * insert list2 node to list1
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        //结果链表的头结点
        ListNode head = list1;

        //当前插入位置的前驱节点
        ListNode pre = null;

        //当前插入位置的后继节点
        ListNode cur1 = list1;
        //链表2剩余节点的头结点
        ListNode cur2 = list2;

        //取出链表2的头结点 插入链表1中
        while (cur2 != null) {
            //如果插入位置已经到了链表1的尾部，则直接将链表2剩余的部分接在链表1最后
            if (cur1 == null) {
                pre.next = cur2;
                break;
            }

            if(cur1.val > cur2.val) {
                //如果当前插入位置正确
                ListNode temp = cur2.next;
                cur2.next = cur1;
                if(pre != null) {
                    pre.next = cur2;
                } else {
                    head = cur2;
                }
                //当前插入位置的前驱节点变为刚刚插入的节点
                pre = cur2;
                //链表2剩余的头结点变更
                cur2 = temp;
            } else {
                //如果插入位置不正确，则向后移动一位
                pre = cur1;
                cur1 = cur1.next;
            }
        }
        return head;
    }

    /**
     * Recursive 1
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        ListNode node = mergeTwoLists1(list1.next, list2);
        ListNode head = node.val >= list1.val ? list1 : node;
        ListNode pre = null;
        //find the right position to insert node list1
        while (node != null && node.val < list1.val) {
            pre = node;
            node = node.next;
        }
        if(pre != null) {
            pre.next = list1;
        }
        list1.next = node;
        return head;
    }

    /**
     * Recursive 2
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        ListNode head;
        if(list1.val <= list2.val) {
            head = list1;
            head.next = mergeTwoLists2(list1.next, list2);
        } else {
            head = list2;
            head.next = mergeTwoLists2(list1, list2.next);
        }
        return head;
    }

    /**
     * Iterative with dummy node
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
