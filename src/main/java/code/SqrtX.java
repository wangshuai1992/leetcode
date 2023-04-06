package code;

/**
 * https://leetcode.com/problems/sqrtx/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 17:10
 */
public class SqrtX {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        long ans = 0;
        for (long i = 0; i * i <= x; i++) {
            ans = i;
        }
        return (int)ans;
    }

    /**
     * 二分查找
     */
    public int mySqrt1(int x) {
        long left = 0;
        long right = x;
        // 找到第一个>target的位置
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int)(left - 1);
    }

}
