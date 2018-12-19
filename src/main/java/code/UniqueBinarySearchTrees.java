package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-18 11:10
 */
public class UniqueBinarySearchTrees {

    Map<Integer, Integer> memo = new HashMap<>();

    /**
     * Recursive
     *
     * divide 1 - n in 3 sections : left -> root -> right
     * for each root position, there are F(left) * F(right) trees
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }

        if(memo.containsKey(n)) {
            return memo.get(n);
        }

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += numTrees(i) * numTrees(n - 1 - i);
        }
        memo.put(n, sum);
        return sum;
    }

    /**
     * Dynammic Programming
     *
     * dp[i] means the result when we have i nums, i ranges from 0 to n
     *
     * @param n
     * @return
     */
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        // i : current length
        for(int i = 2; i <= n; i++) {
            //j : index of tree root
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    /**
     * Mathematical Deduction
     *
     * the sequence of G(n) function results is known as Catalan number C(n)
     *
     * C(0) = 1 ; C(n + 1) = 2(2n+1)/n+2 * C(n)
     *
     * @param n
     * @return
     */
    public int numTrees3(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long catalan = 1;
        for (int i = 0; i < n; ++i) {
            catalan = catalan * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) catalan;
    }

}
