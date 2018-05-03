package code;

/**
 * https://leetcode.com/problems/max-consecutive-ones/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-28 16:19
 */
public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int temp = 0;
        for(int n : nums) {
            if(n == 1) {
                temp ++;
                result = Math.max(result, temp);
                continue;
            }
            temp = 0;
        }
        return result;
    }

}
