package code;

/**
 * https://leetcode.com/problems/jump-game/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-21 02:06
 */
public class JumpGame {

    /**
     * recusive with memo
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        Boolean[] memo = new Boolean[nums.length];
        return canJump(nums, 0, memo);
    }

    private boolean canJump(int[] nums, int start, Boolean[] memo) {
        if (memo[start] != null) {
            return memo[start];
        }
        if (nums.length <= start + 1) {
            memo[start] = true;
            return true;
        }
        if (nums[start] == 0) {
            memo[start] = false;
            return false;
        }
        for (int i = start + 1; i <= (start + nums[start]); i ++) {
            if (canJump(nums, i, memo)) {
                memo[i] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }

    /**
     * dp
     *
     * @param nums
     * @return
     */
    public boolean canJump1(int[] nums) {
        // 记录位置i做起点是否能到达终点
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= nums.length - 1) {
                dp[i] = true;
                continue;
            }
            for (int j = i + nums[i]; j > i; j--) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    /**
     * dp2
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return true;
        }
        // 定义一个数，为达到最后最后一个结点最小需要的步数
        int minStep = 1;
        // 从倒数第二个往第二个开始遍历
        for (int i = length - 2; i > 0; i--) {
            if (nums[i] < minStep) {
                // 如果当前元素的值小于最小步数,则到达最后一个元素的最小步数+1;
                minStep++;
            } else {
                // 如果当前元素的值大于或等于最小步数，则一定能到达最后一个元素，
                // 此时可以就当前元素认为是最后一个元素，并对于前一个元素来说最小步数为1;
                minStep = 1;
            }
        }
        //此时minStep为达到"最后一个元素"(并不是nums[length-1])的最小步数，只要判断第一个元素的值是否大于或等于最小步数就可以了;
        return nums[0] >= minStep;
    }

    /**
     * 贪心
     *
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        int len = nums.length;
        // 最远可以达到的位置
        int m = 0;
        for (int i = 0; i < len; i++) {
            // 如果遍历到了比此时能跳最远距离还远的地方，则不可能，直接返回false
            if (i > m) {
                return false;
            }
            m = Math.max(m, nums[i] + i);
        }
        return true;
    }

}
