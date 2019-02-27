package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-01-16 17:56
 */
public class HouseRobber {

    /**
     * backtracking
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return backtrack(nums, 0);
    }

    private int backtrack(int[] nums, int start) {
        if(start >= nums.length) {
            return 0;
        } else {
            int take = nums[start] + backtrack(nums, start + 2);
            int notake = backtrack(nums, start + 1);
            return Math.max(take, notake);
        }
    }

    /**
     * backtrack with memory / O(n) time , O(n) space
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return backtrack1(nums, 0, memo);
    }

    private int backtrack1(int[] nums, int start, int[] memo) {
        if(start >= nums.length) {
            return 0;
        } else {
            if(memo[start] != -1) {
                return memo[start];
            }
            int take = nums[start] + backtrack1(nums, start + 2, memo);
            int notake = backtrack1(nums, start + 1, memo);
            memo[start] = Math.max(take, notake);
            return memo[start];
        }
    }

    /**
     * dp / O(n) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int[] dp = new int[nums.length + 2];
        for(int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]);
        }
        return dp[0];
    }

    /**
     * dp / O(n) time, O(1) space
     *
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        int prev1 = 0;
        int prev2 = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            int max = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = max;
        }
        return prev1;
    }

}
