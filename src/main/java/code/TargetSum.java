package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/target-sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-15 09:23
 */
public class TargetSum {

    int res;

    /**
     * Backtracking / Brute Force
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, 0, target);
        return res;
    }

    private void backtrack(int[] nums, int start, int remain) {
        if (start == nums.length) {
            if (remain == 0) {
                res++;
            }
            return;
        }

        backtrack(nums, start + 1, remain + nums[start]);
        backtrack(nums, start + 1, remain - nums[start]);
    }

    /**
     * Recursive / Brute Force
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays1(int[] nums, int target) {
        return recurse(nums, 0, target);
    }

    private int recurse(int[] nums, int start, int target) {
        if (start == nums.length) {
            return target == 0 ? 1 : 0;
        }
        return recurse(nums, start + 1, target + nums[start]) + recurse(nums, start + 1, target - nums[start]);
    }

    /**
     * Recurse with memo
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        //use Map<Integer,Map<Integer,Integer>> is better
        int[][] memo = new int[nums.length][2001];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return recurseWithMemo(nums, 0, 0, target, memo);
    }

    private int recurseWithMemo(int[] nums, int start, int sum, int target, int[][] memo) {
        if (start == nums.length) {
            return sum == target ? 1 : 0;
        } else {
            // +1000 to prevent index out of bounds when sum is negetive
            if (memo[start][sum + 1000] != Integer.MIN_VALUE) {
                return memo[start][sum + 1000];
            }
            int add = recurseWithMemo(nums, start + 1, sum + nums[start], target, memo);
            int subtract = recurseWithMemo(nums, start + 1, sum - nums[start], target, memo);
            memo[start][sum + 1000] = add + subtract;
            return memo[start][sum + 1000];
        }
    }

    /**
     * dp[i][j] refers to the number of assignments which can lead to a sum of j upto the ith index.
     *
     * dp[i][sum + nums[i]] += dp[i-1][sum]
     * dp[i][sum − nums[i]] += dp[i−1][sum]
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays3(int[] nums, int target) {
        if (nums.length == 0) {
            return target == 0 ? 1 : 0;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (Math.abs(target) > Math.abs(totalSum)) {
            return 0;
        }

        int[][] dp = new int[nums.length][2001];
        dp[0][1000 + nums[0]] = 1;
        // when nums[0] = 0, the index is the same as above
        dp[0][1000 - nums[0]] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1000 - totalSum; j <= 1000 + totalSum; j++) {
                if (dp[i - 1][j] == 0) {
                    continue;
                }
                dp[i][j + nums[i]] += dp[i - 1][j];
                dp[i][j - nums[i]] += dp[i - 1][j];
            }
        }
        return dp[nums.length - 1][1000 + target];
    }

    /**
     * The original problem statement is equivalent to:
     * - Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
     *
     * Let P be the positive subset and N be the negative subset
     *
     * we have :
     * sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     * 2 * sum(P) = target + sum(nums)
     *
     * So the original problem has been converted to a subset sum problem as follows:
     * - Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
     *
     * Note that the above formula has proved that target + sum(nums) must be even
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays4(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (target > sum || ((target + sum) & 1) == 1) {
            return 0;
        }
        return subsetSum2D(nums, (target + sum) >> 1);
    }

    /**
     * calculate how many subsets in nums that has the sum of target (2D - DP)
     * dp[i][j] means how many subsets in (0, i) that sums j
     *
     * 0 <= i <= nums.length - 1
     * dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]]
     *
     * - Partition Equal Subset Sum => see https://leetcode.com/problems/partition-equal-subset-sum/
     *
     * @param nums
     * @param target
     * @return
     */
    private int subsetSum2D(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < target + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[dp.length - 1][target];
    }

    /**
     * 2d solution always depends on values from row above and it doesn't depend on other rows.
     *
     * As a result, we can only fill up one array on every iteration over 'nums' and then reuse it on the next
     * iteration (as if we would be moving to next row in 2d table)
     *
     * @param nums
     * @param target
     * @return
     */
    private int subsetSum1D(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : nums) {
            // when j < n, we can just use the pre row's value
            for (int j = target; j >= n; j--) {
                dp[j] += dp[j - n];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        System.out.println(new TargetSum().findTargetSumWays4(arr, 3));
    }

}
