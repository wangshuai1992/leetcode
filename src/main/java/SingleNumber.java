/**
 * https://leetcode.com/problems/single-number/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-17 18:05
 */
public class SingleNumber {

    /*
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }

        int setSum = 0;
        for(int i : set) {
            setSum += i;
        }

        int sum = 0;
        for(int i : nums) {
            sum += i;
        }

        return setSum * 2 - sum;
    }
    */

    /**
     * a ^ 0 = a ; a ^ a = 0;
     * a ^ b ^ a = a ^ a ^ b = 0 ^ b = b
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        for(int i=1; i<nums.length; i++) {
            nums[0] = nums[i] ^ nums[0];
        }
        return nums[0];
    }



}
