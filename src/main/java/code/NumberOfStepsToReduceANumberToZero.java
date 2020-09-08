package code;

/**
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-04-27 19:33
 */
public class NumberOfStepsToReduceANumberToZero {

    /**
     * recursive
     *
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        if (num <= 0) {
            return 0;
        }
        if ((num & 1) == 1) {
            return numberOfSteps(num - 1) + 1;
        } else {
            return numberOfSteps(num >> 1) + 1;
        }
    }

    public int numberOfSteps2(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            if ((i & 1) == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i >> 1] + 1;
            }
        }
        return dp[num];
    }

}
