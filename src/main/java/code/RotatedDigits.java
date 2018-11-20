package code;

/**
 * https://leetcode.com/problems/rotated-digits/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-16 17:26
 */
public class RotatedDigits {

    /**
     * brute force
     *
     * @param n
     * @return
     */
    public int rotatedDigits(int n) {
        int result = 0;
        for(int i = 1; i <= n; i++) {
            if(isGoodNum(i)) {
                result ++;
            }
        }
        return result;
    }

    private boolean isGoodNum(int num) {
        StringBuilder builder = new StringBuilder();
        for(char c : String.valueOf(num).toCharArray()) {
            if(c == '0' || c == '1' || c == '8') {
                builder.append(c);
            } else if(c == '2') {
                builder.append('5');
            } else if(c == '5') {
                builder.append('2');
            } else if(c == '6') {
                builder.append('9');
            } else if(c == '9') {
                builder.append('6');
            } else {
                return false;
            }
        }
        int newNum = Integer.parseInt(builder.toString());
        return newNum != num;
    }

    /**
     * use dp to reduce calculating the same sub problems.
     *
     * dp[i] = 0, invalid number
     * dp[i] = 1, valid and same number
     * dp[i] = 2, valid and different number
     *
     * In brute force, e.g. N=112, you need to judge digit by digit for N=1 to 112. time complexity: O(N * #digits)
     * And the same pattern like 11 is actually re-calculated because you already traversed N=11, and know it's a valid
     * and same pattern, but you judge it again when you meet N=112.
     *
     * In DP, N=112 is separated into 2 sub-problems, N=11 and N=2, and since these two patterns have already been
     * caculated, we can get the answer for 112 directly. time complexity: O(N) space complexity: O(N)
     *
     * @param n
     * @return
     */
    public int rotatedDigits1(int n) {
        int result = 0;

        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            if(i < 10) {
                if(i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2;
                    result ++;
                } else if(i == 0 || i == 1 || i == 8) {
                    dp[i] = 1;
                } else {
                    dp[i] = 0;
                }
            } else {
                int a = i / 10;
                int b = i % 10;
                if(dp[a] != 0 && dp[b] != 0 && (dp[a] == 2 || dp[b] == 2)) {
                    dp[i] = 2;
                    result ++;
                } else if(dp[a] == 1 && dp[b] == 1) {
                    dp[i] = 1;
                } else {
                    dp[i] = 0;
                }
            }
        }

        return result;
    }

}
