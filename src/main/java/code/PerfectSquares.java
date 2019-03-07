package code;

import java.util.*;

/**
 * https://leetcode.com/problems/perfect-squares/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-01 09:51
 */
public class PerfectSquares {

    /**
     * Recurse with memorization
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[0] = 0;
        return recurse(n, memo);
    }

    private int recurse(int n, int[] memo) {
        if (n <= 0) {
            return 0;
        }
        if (memo[n] != Integer.MAX_VALUE) {
            return memo[n];
        }
        for (int i = 1; i * i <= n; i++) {
            memo[n] = Math.min(memo[n], recurse(n - i * i, memo) + 1);
        }
        return memo[n];
    }

    /**
     * Dynamic Programming
     *
     * @param n
     * @return
     */
    public int numSquares1(int n) {
        if (n <= 0) {
            return 0;
        }

        // dp[i] = the least number of perfect square numbers
        // which sum to i. Note that dp[0] is 0.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            // For each i, it must be the sum of some number (i - j*j) and
            // a perfect square number (j*j).
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[dp.length - 1];
    }

    private static List<Integer> staticDp = new ArrayList<>();

    static {
        staticDp.add(0);
    }

    /**
     * static Dynamic Programming
     *
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        if (n <= 0) {
            return 0;
        }

        while (staticDp.size() <= n) {
            int m = staticDp.size();
            int count = Integer.MAX_VALUE;
            for (int i = 1; i * i <= m; i++) {
                count = Math.min(count, staticDp.get(m - i * i) + 1);
            }
            staticDp.add(count);
        }
        return staticDp.get(n);
    }

    /**
     * Mathematical
     *
     * Based on Lagrange's Four Square theorem, there
     * are only 4 possible results: 1, 2, 3, 4.
     *
     * @param n
     * @return
     */
    public int numSquares3(int n) {
        // If n is a perfect square, return 1.
        if (isSquare(n)) {
            return 1;
        }

        // The result is 4 if and only if n can be written in the
        // form of 4^k*(8*m + 7). Please refer to Legendre's three-square theorem.

        // n%4 == 0
        while ((n & 3) == 0) {
            n >>= 2;
        }
        // n%8 == 7
        if ((n & 7) == 7) {
            return 4;
        }

        // Check whether 2 is the result.
        int sqrtN = (int) (Math.sqrt(n));
        for (int i = 1; i <= sqrtN; i++) {
            if (isSquare(n - i * i)) {
                return 2;
            }
        }
        return 3;
    }

    private boolean isSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

    /**
     * Breadth-First Search
     *
     * @param n
     * @return
     */
    public int numSquares4(int n) {
        if (n <= 0) {
            return 0;
        }

        // perfectSquares contain all perfect square numbers which
        // are smaller than or equal to n.
        List<Integer> perfectSquares = new ArrayList<>();
        // cntPerfectSquares[i - 1] = the least number of perfect
        // square numbers which sum to i.
        int[] cntPerfectSquares = new int[n];

        // Get all the perfect square numbers which are smaller than
        // or equal to n.
        for (int i = 1; i * i <= n; i++) {
            perfectSquares.add(i * i);
            cntPerfectSquares[i * i - 1] = 1;
        }

        // If n is a perfect square number, return 1 immediately.
        if (perfectSquares.get(perfectSquares.size() - 1) == n) {
            return 1;
        }

        // Consider a graph which consists of number 0, 1,...,n as
        // its nodes. Node j is connected to node i via an edge if
        // and only if either j = i + (a perfect square number) or
        // i = j + (a perfect square number). Starting from node 0,
        // do the breadth-first search. If we reach node n at step
        // m, then the least number of perfect square numbers which
        // sum to n is m. Here since we have already obtained the
        // perfect square numbers, we have actually finished the
        // search at step 1.
        Queue<Integer> searchQ = new ArrayDeque<>();
        for (int i : perfectSquares) {
            searchQ.offer(i);
        }

        int currCntPerfectSquares = 1;
        while (!searchQ.isEmpty()) {
            currCntPerfectSquares++;

            int searchQSize = searchQ.size();
            for (int i = 0; i < searchQSize; i++) {
                int tmp = searchQ.remove();
                // Check the neighbors of node tmp which are the sum
                // of tmp and a perfect square number.
                for (int j : perfectSquares) {
                    if (tmp + j == n) {
                        // We have reached node n.
                        return currCntPerfectSquares;
                    } else if ((tmp + j < n) && (cntPerfectSquares[tmp + j - 1] == 0)) {
                        // If cntPerfectSquares[tmp + j - 1] > 0, this is not
                        // the first time that we visit this node and we should
                        // skip the node (tmp + j).
                        cntPerfectSquares[tmp + j - 1] = currCntPerfectSquares;
                        searchQ.offer(tmp + j);
                    } else if (tmp + j > n) {
                        // We don't need to consider the nodes which are greater
                        // than n.
                        break;
                    }
                }
            }
        }
        return 0;
    }

}
