package code;

import java.util.*;

/**
 * https://leetcode.com/problems/two-sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-02-27 15:54
 */
public class TwoSum {

    /**
     * Brute Force / O(n^2) time, O(1) space
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    /**
     * Two-pass Hash Table / O(n) time , O(n) space
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        //check from i=0 to i=length-1 when the same number will be mapped to a larger index.
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[2];
    }

    /**
     * One-pass Hash Table / O(n) time, O(n) space
     *
     * While we iterate and inserting elements into the table, we also look back to check if current element's
     * complement already exists in the table. If it exists, we have found a solution and return immediately.
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }


}
