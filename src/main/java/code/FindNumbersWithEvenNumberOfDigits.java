package code;

/**
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 14:26
 */
public class FindNumbersWithEvenNumberOfDigits {

    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int n : nums) {
            int numCount = 0;
            while (n > 0) {
                numCount++;
                n /= 10;
            }
            if ((numCount & 1) == 0) {
                ans++;
            }
        }
        return ans;
    }

}
