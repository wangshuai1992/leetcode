package code;

import java.math.BigInteger;

/**
 * https://leetcode.com/problems/climbing-stairs/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-24 10:16
 */
public class ClimbingStairs {

    /**
     * Recursive with memo
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return recurse(n, memo);
    }

    private int recurse(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memo[n] == 0) {
            memo[n] = recurse(n - 1, memo) + recurse(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * DP / O(n) space
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * DP / O(1) space / Fibonacci Number
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        //dp[0]
        int preTwo = 1;
        //dp[1]
        int preOne = 1;
        int result = preTwo + preOne;
        for (int i = 2; i <= n; i++) {
            result = preTwo + preOne;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }

    /**
     * Using Combination
     *
     * Start with all 1s. If you add a single 2, you have C(n-1,1) combinations of where you place the 2.
     * Then add a second 2, you have C(n-2,2) combinations. You can only add more 2s until you run out of 1s
     * to remove (i.e. where n < k).
     *
     * note to memoize the factorials
     */
    private BigInteger[] factorials;

    public int climbStairs3(int n) {
        factorials = new BigInteger[100];
        int result = 0;
        // i means how many 2s
        for (int i = 0; i <= n / 2; i++) {
            // how many buckets to place 2 and 1
            int bucket = n - i;
            result += getFactorial(bucket).divide(getFactorial(bucket - i).multiply(getFactorial(i))).intValue();
        }
        return result;
    }

    private BigInteger getFactorial(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        if (factorials[n] == null) {
            factorials[n] = getFactorial(n - 1).multiply(BigInteger.valueOf(n));
        }
        return factorials[n];
    }

    /**
     * fibonacci number formula
     *
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibn / sqrt5);
    }

    /**
     * Binets Method / uses matrix multiplication to obtain the nth Fibonacci Number
     *
     * @param n
     * @return
     */
    public int climbStairs5(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    private int[][] pow(int[][] a, int n) {
        //单位矩阵
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs3(35));
    }

}
