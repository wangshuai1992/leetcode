import java.util.Arrays;

/**
 * https://leetcode.com/problems/array-partition-i/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-13 17:09
 */
public class ArrayPartitionI {

    public int arrayPairSum(int[] nums) {
        //按照题目的情况，可以采用计数排序，时间复杂度更低
        Arrays.sort(nums);
        int sum = 0;
        for(int i=0; i<nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2};
        System.out.println(new ArrayPartitionI().arrayPairSum(nums));
    }

}
