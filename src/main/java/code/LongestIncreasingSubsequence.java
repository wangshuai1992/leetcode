package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-08 10:21
 */
public class LongestIncreasingSubsequence {

    /**
     * Recursive / O(2^n) time, O(n) time
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return recurse(nums, Integer.MIN_VALUE, 0);
    }

    private int recurse(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + recurse(nums, nums[curpos], curpos + 1);
        }
        int nottaken = recurse(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }

    /**
     * Recurse with memo / O(n^2) time, O(n^2) space
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return recurseWithMemo(nums, -1, 0, memo);
    }

    private int recurseWithMemo(int[] nums, int preIndex, int curIndex, int[][] memo) {
        if (curIndex == nums.length) {
            return 0;
        }
        if (memo[preIndex + 1][curIndex] >= 0) {
            return memo[preIndex + 1][curIndex];
        }
        int taken = 0;
        if (preIndex < 0 || nums[curIndex] > nums[preIndex]) {
            taken = 1 + recurseWithMemo(nums, curIndex, curIndex + 1, memo);
        }
        int nottaken = recurseWithMemo(nums, preIndex, curIndex + 1, memo);
        memo[preIndex + 1][curIndex] = Math.max(taken, nottaken);
        return memo[preIndex + 1][curIndex];
    }

    /**
     * DP / O(n^2) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //dp[i] 表示从0到第i个数中LIS的长度（第i个数必须包含在LIS序列内）
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxVal = Math.max(dp[j] + 1, maxVal);
                }
            }
            dp[i] = maxVal;
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * DP with binary search / O(nlogn) time, O(n) space
     * dp array is meant to store the increasing subsequence formed by including the currently encountered element.
     *
     * example:
     *
     * input: [0, 8, 4, 12, 2]
     *
     * dp: []     len = 0, num = 0, i = 0
     *
     * dp: [0]    len = 1, num = 8, i = 1
     *
     * dp: [0, 8]      len = 2, num = 4, i = 1
     *
     * dp: [0, 4]      len = 2, num = 12, i = 2
     *
     * dp: [0, 4, 12]     len = 3, num = 2, i = 1
     *
     * dp: [0 , 2, 12] which is not the longest increasing subsequence, but length of dp array results in
     * length of Longest Increasing Subsequence.
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS3(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            // when not found, binarySearch return -(insertPoint) - 1
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }


    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 4};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(arr));
    }

}
