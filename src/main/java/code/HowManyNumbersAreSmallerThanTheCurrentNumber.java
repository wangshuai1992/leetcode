package code;

/**
 * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-04-29 09:28
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    /**
     * O(n^2)
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int n = nums[i];
            for (int num : nums) {
                if (n > num) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * counting  O(n)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int[] count = new int[101];
        int[] result = new int[nums.length];

        // count[i] 记录数字i出现的次数
        for (int n : nums) {
            count[n] += 1;
        }

        // count[i] 记录小于或等于(包括自身)数字i的数的个数
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n == 0) {
                result[i] = 0;
            } else {
                result[i] = count[n - 1];
            }
        }
        return result;
    }

}
