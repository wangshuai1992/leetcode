package code;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-15 15:31
 */
public class PartitionEqualSubsetSum {

    /**
     * backtrack with memorization / Recursive
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if((sum & 1) == 1) {
            return false;
        }

        Boolean[][] memo = new Boolean[nums.length][sum + 1];
        sum /= 2;
        return backtrack(nums, 0, sum, memo);
    }

    private boolean backtrack(int[] nums, int start, int remain, Boolean[][] memo) {
        if(start == nums.length) {
            return remain == 0;
        }
        if(remain == 0) {
            //sum reached
            return true;
        }
        if(remain < 0) {
            return false;
        }
        if(memo[start][remain] == null) {
            memo[start][remain] = backtrack(nums, start + 1, remain - nums[start], memo) || backtrack(nums, start + 1, remain, memo);
        }
        return memo[start][remain];
    }

    /**
     * Dynamic Programming
     *
     * dp[i][j] means if subset from 0 to i can sum to j
     *
     * dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]  (needs to check for index bounds)
     *
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if((sum & 1) == 1) {
            return false;
        }

        sum /= 2;
        boolean[][] dp = new boolean[nums.length][sum + 1];

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= nums[i]) {
                    dp[i][j] |= dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][sum];
    }

    /**
     * 1D DP
     *
     * 2d solution always depends on values from row above and it doesn't depend on other rows.
     *
     * As a result, we can only fill up one array on every iteration over 'nums' and then reuse it on the next
     * iteration (as if we would be moving to next row in 2d table)
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if((sum & 1) == 1) {
            return false;
        }

        sum /= 2;
        boolean[] dp = new boolean[sum + 1];

        dp[0] = true;
        for(int n : nums) {
            // when j < n, we can just use the pre row's value
            for(int j = sum; j >= n; j--) {
                dp[j] |= dp[j - n];
            }
        }
        return dp[sum];
    }

}
