package code;

/**
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 15:18
 */
public class ConvertBinaryNumberInALinkedListToInteger {

    public int getDecimalValue(ListNode head) {
        ListNode node = head;
        int ans = 0;
        while (node != null) {
            ans = (ans << 1) + node.val;
            node = node.next;
        }
        return ans;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
