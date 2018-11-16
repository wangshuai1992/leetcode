package code;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-14 16:27
 */
public class ProductOfArrayExceptSelf {

    /**
     * 结果数组中的每个位置的数 = 原数组中位于该位置左边的所有数的积 * 右边所有数的积
     *
     * 第一步 ： 从左至右， 将结果数组的每个位置的值赋为它左边所有数的积
     *
     * 第二步 ： 从右至左， 将结果数组的每个位置的当前值乘上它右边所有数的积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;

        for(int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int rightTemp = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            res[i] *= rightTemp;
            rightTemp *= nums[i];
        }
        return res;
    }

}
