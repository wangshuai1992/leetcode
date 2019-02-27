package code;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-02-27 17:12
 */
public class TwoSumII {

    /**
     * Two Pointer
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                return new int[]{start + 1, end + 1};
            }
            if (sum < target) {
                start++;
            }
            if (sum > target) {
                end--;
            }
        }
        return new int[2];
    }

}
