package code;

/**
 * https://leetcode.com/problems/search-insert-position/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-19 02:33
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 找到第一个>=target的位置
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
