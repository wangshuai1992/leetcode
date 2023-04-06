package code;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-18 01:02
 */
public class SearchInRotatedSortedArray {

    /**
     * 1. 只有在顺序区间内才可以通过区间两端的数值判断target是否在其中。
     * 2. 判断顺序区间还是乱序区间，只需要对比 left 和 right 是否是顺序对即可，left <= right，顺序区间，否则乱序区间。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                // left 到 mid 是顺序区间
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // mid 到 right 是顺序区间
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
