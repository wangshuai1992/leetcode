package code;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-11-09 18:09
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int newIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[newIndex]) {
                swap(nums, newIndex + 1, i);
                newIndex++;
            }
        }
        return newIndex + 1;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

}
