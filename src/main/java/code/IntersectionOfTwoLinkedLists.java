package code;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2021-04-12 17:54
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * brute force
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aCur = headA;
        while (aCur != null) {
            ListNode bCur = headB;
            while (bCur != null) {
                if (aCur == bCur) {
                    return aCur;
                }
                bCur = bCur.next;
            }
            aCur = aCur.next;
        }
        return null;
    }

    /**
     * https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode aCur = headA;
        ListNode bCur = headB;
        while (aCur != bCur) {
            aCur = aCur == null ? headB : aCur.next;
            bCur = bCur == null ? headA : bCur.next;
        }
        return aCur;
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
