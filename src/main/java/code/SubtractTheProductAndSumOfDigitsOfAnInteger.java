package code;

/**
 * https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 10:54
 */
public class SubtractTheProductAndSumOfDigitsOfAnInteger {

    public int subtractProductAndSum(int n) {
        if (n == 0) {
            return 0;
        }
        int product = 1;
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            product *= d;
            sum += d;
            n /= 10;
        }
        return product - sum;
    }

}
