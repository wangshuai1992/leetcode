package code;

/**
 * https://leetcode.com/problems/reverse-integer/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-17 14:52
 */
public class ReverseInteger {

    /**
     * O(logn) time
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7) || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                // 溢出
                return 0;
            }
            res = res * 10 + pop;
            x = x / 10;
        }
        return res;
    }

}
