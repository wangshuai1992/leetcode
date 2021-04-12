package code;

/**
 * https://leetcode.com/problems/divide-two-integers/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-11-16 18:16
 */
public class DivideTwoIntegers {

    /**
     * https://www.jianshu.com/p/7bba031b11e7
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) {
            return (1 << 31) - 1;
        }
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        int res = 0;
        for (int x = 31; x >= 0; x--) {
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b << x;
            }
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

}
