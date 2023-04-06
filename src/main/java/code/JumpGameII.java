package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/jump-game-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-21 03:33
 */
public class JumpGameII {

    /**
     * dp
     * O(n^2) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        // 记录以数组中间某个元素做起点到达末尾最少需要的步数
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int minStep = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + nums[i] && j < nums.length; j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    minStep = Math.min(minStep, dp[j] + 1);
                }
            }
            dp[i] = minStep;
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
    }

    /**
     * 从前往后dp
     * O(n^2) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // dp[i] 表示到第 i 个位置需要的最少步数
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && j <= nums[i] + i; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }

    /**
     * 优化dp
     * O(n) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // dp[i] 表示到第 i 个位置需要的最少步数
        int[] dp = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            // 如果当前能到达的位置<最远需要到达的位置, j+1
            while (j + nums[j] < i) {
                j++;
            }
            dp[i] = dp[j] + 1;
        }
        return dp[n - 1];
    }

    /**
     * 每次在上次能跳到的范围（end）内选择一个能跳的最远的位置（也就是能跳到max_far位置的点）作为下次的起跳点
     *
     * @param nums
     * @return
     */
    public int jump3(int[] nums) {
        // 目前能跳到的最远位置
        int maxFar = 0;
        // 跳跃次数
        int step = 0;
        // 上次跳跃可达范围右边界（下次的最右起跳点）
        int end = 0;
        // 注意这里i不访问最后一个位置 否则step会+1
        for (int i = 0; i < nums.length - 1; i++) {
            maxFar = Math.max(maxFar, i + nums[i]);
            // 到达上次跳跃能到达的右边界了
            if (i == end) {
                // 目前能跳到的最远位置变成了下次起跳位置的右边界
                end = maxFar;
                // 进入下一次跳跃
                step++;
            }
            if (end >= nums.length - 1) {
                return step;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1};
        System.out.println(new JumpGameII().jump1(nums));
    }

}
