package code;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-26 17:31
 */
public class DeleteNodeInALinkedList {

    /**
     * Swap with Next Node
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
