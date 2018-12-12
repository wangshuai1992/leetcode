package code;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-10 09:15
 */
public class FindTheDuplicateNumber {

    /**
     * Cycle Detection / O(n) time and O(1) space
     *
     * use i -> nums[i] to construction a singly linked list, due to duplicate values in nums, the linked list has at
     * least a cycle, the result will be the entrance of the cycle (searching from nums[0], note that nums[0] is
     * garanteed not equals to 0 because values is 1 to len-1)
     *
     * nums[x] = entrance and nums[y] = entrance, that is what makes the entrance value the result
     *
     * positions like nums[x] = x will not be entered unless it is the result(because if there is only one x in the
     * array, and we begin with nums[0], there will be no way we get another num y makes nums[y] = x and x != y)
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (slow == 0 || fast != slow) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int restart = 0;
        while (restart != slow) {
            restart = nums[restart];
            slow = nums[slow];
        }
        return restart;
    }

}
