package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-18 17:50
 */
public class ThreeSum {

    /**
     * Sorting and two pointers / O(n^2) time
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 0 || nums[0] > 0 || nums[nums.length - 1] < 0) {
            return new ArrayList<>();
        }

        // 取第一个数，然后two pointer向中间寻找符合条件的组合
        List<List<Integer>> resList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            // left 从 i + 1开始 避免重复
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    resList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left - 1] == nums[left]) {
                        // 跳过重复数
                        left++;
                    }
                    while (left < right && nums[right + 1] == nums[right]) {
                        // 跳过重复数
                        right--;
                    }
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return resList;
    }
}
