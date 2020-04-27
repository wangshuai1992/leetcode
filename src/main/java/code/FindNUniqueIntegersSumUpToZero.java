package code;

/**
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 16:42
 */
public class FindNUniqueIntegersSumUpToZero {

    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int x = 1;
        boolean isOdd = (n & 1) == 1;
        for (int i = 0; i < n; i++) {
            if(i == 0 && isOdd) {
                ans[i] = 0;
            } else {
                ans[i] = x;
                ans[i + 1] = -x;
                i++;
                x++;
            }
        }
        return ans;
    }

}
