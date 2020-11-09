package code;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-25 17:22
 */
public class MergeKSortedLists {

    /**
     * brute force (merge and sort) / O(nlogn)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> valueList = new ArrayList<>();
        // merge
        for (ListNode head : lists) {
            ListNode cur = head;

            while (cur != null) {
                valueList.add(cur.val);
                cur = cur.next;
            }
        }

        // sort
        Collections.sort(valueList);

        // re-create
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int value : valueList) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    /**
     * Compare one by one
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        // 记录每个链表进行到的位置
        Map<Integer, ListNode> map = new HashMap<>();
        for (int i = 0; i < lists.length; i++) {
            map.put(i, lists[i]);
        }
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        boolean isEnd = false;
        while (!isEnd) {
            isEnd = true;
            Map.Entry<Integer, ListNode> entryToTake = null;
            for (Map.Entry<Integer, ListNode> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    isEnd = false;
                    if (entryToTake == null || entryToTake.getValue().val > entry.getValue().val) {
                        entryToTake = entry;
                    }
                }
            }
            if (entryToTake != null) {
                tail.next = entryToTake.getValue();
                tail = tail.next;
                map.put(entryToTake.getKey(), entryToTake.getValue().next);
            }
        }
        return dummy.next;
    }

    /**
     * merge sort
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }

    /**
     * using PriorityQueue / O(nlogk) k为堆深度
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(List<ListNode> lists) {
        Queue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        while (!heap.isEmpty()) {
            tail.next = heap.poll();
            tail = tail.next;
            if (tail.next != null) {
                heap.offer(tail.next);
            }
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
