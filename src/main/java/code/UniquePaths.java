package code;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-11 11:42
 */
public class UniquePaths {

    private int ans = 0;

    /**
     * backtracking / Brute Force
     *
     * 如果mLeft或者nLeft为0 代表剩下的步数只能向下或者向右走 只有一种可能性
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        backtrack(m - 1, n - 1);
        return ans;
    }

    private void backtrack(int mLeft, int nLeft) {
        if (mLeft == 0 || nLeft == 0) {
            ans++;
            return;
        }
        backtrack(mLeft, nLeft - 1);
        backtrack(mLeft - 1, nLeft);
    }

    /**
     * Math / Combination
     *
     * 总共需要移动x = (m-1) * (n-1)步，其中 y = m - 1步为向右移动，问题即转化为将y个右移操作放入x个位置的组合问题
     *
     * combination(x, y) = x! / ((x - y)! * y!)
     * reduce the numerator and denominator and get
     * combination(x, y) = (x * (x - 1) * ... * (x - y + 1)) / y!
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        int x = (m - 1) + (n - 1);
        int y = Math.min(m, n) - 1;

        double res = 1;
        for (int i = 1; i <= y; i++) {
            res = res * (x - i + 1) / i;
        }
        return (int) res;
    }

    /**
     * 2D Dynamic Programming
     *
     * let the number of paths to arrive at a point (i, j) is denoted as dp[i][j],
     * then we have dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 1D DP
     *
     * dp[j](current row) = dp[j](last row) + dp[j-1](current row)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n) {
        if(m < n) {
            //save space
            return uniquePaths3(n, m);
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths1(36, 7));
    }

}
