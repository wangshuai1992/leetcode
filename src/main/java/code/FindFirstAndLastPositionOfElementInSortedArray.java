package code;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-19 01:21
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * iterative / binary search
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = searchFirstGt(nums, target);
        int end = searchFirstGt(nums, target + 1);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, end - 1};
    }

    /**
     * 找到第一个>=target的索引值 找不到则返回数组长度
     */
    private int searchFirstGt(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 找到start
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
