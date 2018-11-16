package code;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-14 17:43
 */
public class FindAllNumbersDisappearedInAnArray {

    /**
     * The basic idea is that we iterate through the input array and mark elements as negative
     * using nums[nums[i] -1] = -nums[nums[i]-1]. In this way all the numbers that we have seen will be marked as negative.
     *
     * In the second iteration, if a value is not marked as negative, it implies we have never seen that index before,
     * so just add it to the return list.
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new LinkedList<>();

        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

}
