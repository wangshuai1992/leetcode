package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/linked-list-components/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-14 17:11
 */
public class LinkedListComponents {

    public int numComponents(ListNode head, int[] g) {
        Set<Integer> set = new HashSet<>();
        for(int i : g) {
            set.add(i);
        }

        int result = 0;
        ListNode cur = head;
        while (cur != null) {
            if(set.contains(cur.val) && (cur.next == null || !set.contains(cur.next.val))) {
                result ++;
            }
            cur = cur.next;
        }
        return result;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
