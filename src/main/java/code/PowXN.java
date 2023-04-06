package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/powx-n/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 14:15
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class PowXN {

    public double myPow(double x, int n) {
        if (x == 1 || n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        boolean negative = (n < 0);
        double ans = 1;
        long absN = Math.abs((long) n);
        for (long i = 0; i < absN; i++) {
            if (negative) {
                ans /= x;
            } else {
                ans *= x;
            }
        }
        return ans;
    }

    public double myPow1(double x, int n) {
        if (x == 1 || n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        int m = n / 2;
        return myPow1(x, m) * myPow1(x, n - m);
    }

    public double myPow2(double x, int n) {
        Map<Integer, Double> memo = new HashMap<>();
        return myPowWithMemo(x, n, memo);
    }

    private double myPowWithMemo(double x, int n, Map<Integer, Double> memo) {
        if (x == 1 || n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        int m = n / 2;
        if (memo.get(m) == null) {
            memo.put(m, myPowWithMemo(x, m, memo));
        }
        if (memo.get(n - m) == null) {
            memo.put(n - m, myPowWithMemo(x, n - m, memo));
        }
        double ans = memo.get(m) * memo.get(n - m);
        memo.put(n, ans);
        return ans;
    }

    /**
     * 快速幂 + 递归
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow3(double x, int n) {
        long longN = n;
        return longN >= 0 ? quickMul(x, longN) : 1.0 / quickMul(x, -longN);
    }

    private double quickMul(double x, long longN) {
        if (longN == 0) {
            return 1.0;
        }
        double y = quickMul(x, longN / 2);
        return longN % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 快速幂 + 迭代
     * https://leetcode.cn/problems/powx-n/solution/powx-n-by-leetcode-solution/
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow4(double x, int n) {
        long longN = n;
        return longN >= 0 ? quickMul1(x, longN) : 1.0 / quickMul1(x, -longN);
    }

    private double quickMul1(double x, long longN) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double xContribute = x;
        // 在对 longN 进行二进制拆分的同时计算答案
        while (longN > 0) {
            if (longN % 2 == 1) {
                // 如果 longN 二进制表示的最低位为 1，那么需要计入贡献
                ans *= xContribute;
            }
            // 将贡献不断地平方
            xContribute *= xContribute;
            // 舍弃 longN 二进制表示的最低位，这样我们每次只要判断最低位即可
            longN /= 2;
        }
        return ans;
    }

}
