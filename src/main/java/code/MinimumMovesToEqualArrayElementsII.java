package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 16:32
 */
public class MinimumMovesToEqualArrayElementsII {

    public int minMoves2(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            result += nums[j] - nums[i];
            i ++;
            j --;
        }
        return result;
    }

}
