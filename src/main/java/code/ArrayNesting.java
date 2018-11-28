package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/array-nesting/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-26 16:36
 */
public class ArrayNesting {

    /**
     * Brute Force
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            int index = i;
            int count = 1;
            while (nums[index] != i) {
                index = nums[index];
                count ++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    /**
     * keep track of visited set (which will lead to the same loop)
     *
     * @param nums
     * @return
     */
    public int arrayNesting1(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            if(visited.contains(i)) {
                continue;
            }

            visited.add(nums[i]);
            int index = i;
            int count = 1;
            while (nums[index] != i) {
                visited.add(nums[index]);
                index = nums[index];
                count ++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

}
