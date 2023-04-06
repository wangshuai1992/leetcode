package code;

/**
 * https://leetcode.com/problems/decode-ways/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 18:30
 */
public class DecodeWays {

    /**
     * dp
     * O(n) time, O(n) space
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        // dp[i]表示第i个字符开始到末尾的子字符串的解码方式 dp[s.length()]代表空字符串
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            // 当前位置作为单独字符
            dp[i] += (s.charAt(i) == '0' ? 0 : dp[i + 1]);
            // 当前位置和后一个位置组合成为一个字符
            if (i < s.length() - 1 && s.charAt(i) != '0' && ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0')) <= 26) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    /**
     * dp
     * O(n) time, O(1) space
     *
     * @param s
     * @return
     */
    public int numDecodings1(String s) {
        // 代表dp[i + 2] 这个初始化值不会被用到
        int n = -1;
        // 代表dp[i + 1]
        int m = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            int count = 0;
            // 当前位置作为单独字符
            count += (s.charAt(i) == '0' ? 0 : m);
            // 当前位置和后一个位置组合成为一个字符
            if (i < s.length() - 1 && s.charAt(i) != '0' && ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0')) <= 26) {
                count += n;
            }
            n = m;
            m = count;
        }
        return m;
    }



    /**
     * dp
     *
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        // dp[i]代表从开始至 i-1位置 的字符串能有的解码方式个数 dp[0]代表空字符串
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            // 当前位置当做单独字符
            dp[i] += (s.charAt(i - 1) == '0' ? 0 : dp[i - 1]);
            // 当前位置和前一个位置组合成一个字符
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0')) <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

}
