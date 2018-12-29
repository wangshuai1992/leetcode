package code;

/**
 * https://leetcode.com/problems/sort-colors/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-29 16:58
 */
public class SortColors {

    /**
     * two-pass using counting sort / O(n) time
     *
     * First, iterate the array counting number of 0's, 1's, and 2's,
     * then overwrite array with total number of 0's, then 1's and followed by 2's.
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] countArr = new int[3];
        for (int x : nums) {
            countArr[x]++;
        }
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    /**
     * Three Pointers
     *
     * 1 0 2 2 1 0
     * ^         ^
     * L         H
     * M
     *
     * Mid != 0 || 2
     * Mid++
     *
     * 1 0 2 2 1 0
     * ^ ^       ^
     * L M       H
     *
     * Mid == 0
     * Swap Low and Mid
     * Mid++
     * Low++
     *
     * 0 1 2 2 1 0
     * ^ ^     ^
     * L M     H
     *
     * Mid == 2
     * Swap High and Mid
     * High--
     *
     * 0 1 0 2 1 2
     * ^ ^   ^
     * L M   H
     *
     * Mid == 0
     * Swap Low and Mid
     * Mid++
     * Low++
     *
     * 0 0 1 2 1 2
     * ^ ^ ^
     * L M H
     *
     * Mid == 2
     * Swap High and Mid
     * High--
     *
     * 0 0 1 1 2 2
     * ^ ^
     * L M
     * H
     *
     * Mid > High is our exit case
     *
     * @param nums
     */
    public void sortColors1(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 1) {
                mid++;
                continue;
            }
            if (nums[mid] == 0) {
                nums[mid] = nums[low];
                nums[low] = 0;
                low++;
                mid++;
                continue;
            }

            //The reason why we don't need mid++ is because on the right side of mid, it could be a 0 swapped back,
            //that will need be further swapped to left to mid. This is a problem when doing scanning from left to right.
            //on the left side of mid everything is sorted and right side is not
            if (nums[mid] == 2) {
                nums[mid] = nums[high];
                nums[high] = 2;
                high--;
            }
        }
    }

}
