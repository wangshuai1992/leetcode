package code;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/burst-balloons/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-11 17:22
 */
public class BurstBalloons {

    int ans;

    /**
     * brute force
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        List<Integer> numList = new LinkedList<>();
        for (int i : nums) {
            numList.add(i);
        }
        backtrack(numList, 0);
        return ans;
    }

    private void backtrack(List<Integer> numList, int tempCoin) {
        if (numList.isEmpty()) {
            ans = Math.max(ans, tempCoin);
            return;
        }
        int size = numList.size();
        for (int i = 0; i < size; i++) {
            List<Integer> list = new LinkedList<>(numList);
            int val = list.remove(i);
            if (i != 0) {
                val *= list.get(i - 1);
            }
            if (i != size - 1) {
                val *= list.get(i);
            }
            backtrack(list, tempCoin + val);
        }
    }

    /**
     * Divide & Conquer with Memerization
     *
     * The nature way to divide the problem is burst one balloon and separate the balloons into 2 sub sections
     * one on the left and one one the right. However, in this problem the left and right become adjacent and
     * have effects on the maxCoins in the future.
     *
     * Instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.
     * Why is that? Because only the first and last balloons we are sure of their adjacent balloons before hand!
     * For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
     *
     * Think about n balloons if i is the last one to burst, what now?
     * We can see that the balloons is again separated into 2 sections. But this time since the balloon i is the
     * last balloon of all to burst, the left and right section now has well defined boundary and do not affect
     * each other! Therefore we can do either recursive method with memoization or dp.
     *
     * @param iNums
     * @return
     */
    public int maxCoins1(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        //为新数组赋值
        for (int x : iNums) {
            if (x > 0) {
                nums[n++] = x;
            }
        }
        //在头部和尾部补上值为1的气球 方便计算
        nums[0] = nums[n++] = 1;

        //Memerization  memo[i][j] i表示划分的left  j表示划分的right
        //此时n是新数组的长度
        int[][] memo = new int[n][n];
        //这里的left和right都为不包含
        return burst(memo, nums, 0, n - 1);
    }

    private int burst(int[][] memo, int[] nums, int left, int right) {
        if(left + 1 == right) {
            return 0;
        }
        if(memo[left][right] > 0) {
            return memo[left][right];
        }
        int res = 0;
        //i is the last one to burst
        for(int i = left + 1; i < right; i++) {
            res = Math.max(res, nums[i] * nums[left] * nums[right] +
                    burst(memo, nums, left, i) + burst(memo, nums, i, right));
        }
        //add to memorization
        memo[left][right] = res;
        return res;
    }

    /**
     * DP
     *
     * dp[i][j] stands for sub case from i to j (excluded)
     *
     * @param iNums
     * @return
     */
    public int maxCoins2(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) {
            if (x > 0) {
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;

        int[][] dp = new int[n][n];
        //build dp matrix from smallest case
        //k stands for right - left
        for (int k = 2; k < n; ++k) {
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                //iterative all possible index i that burst last
                for (int i = left + 1; i < right; ++i) {
                    dp[left][right] = Math.max(dp[left][right],
                            nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new BurstBalloons().maxCoins(new int[]{3, 1, 5, 8}));
    }

}
