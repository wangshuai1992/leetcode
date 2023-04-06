package code;

import java.util.Objects;

/**
 * https://leetcode.com/problems/count-and-say/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 11:46
 */
public class CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String pre = countAndSay(n - 1);
        StringBuilder builder = new StringBuilder();
        char temp = pre.charAt(0);
        int count = 0;
        for (char c : pre.toCharArray()) {
            if (Objects.equals(temp, c)) {
                count ++;
            } else {
                builder.append(count).append(temp);
                temp = c;
                count = 1;
            }
        }
        builder.append(count).append(temp);
        return builder.toString();
    }

    public String countAndSay1(int n) {
        String[] dp = new String[n + 1];
        dp[1] = "1";
        for (int i = 2; i < dp.length; i++) {
            String pre = dp[i - 1];
            StringBuilder builder = new StringBuilder();
            char temp = pre.charAt(0);
            int count = 0;
            for (char c : pre.toCharArray()) {
                if (Objects.equals(temp, c)) {
                    count ++;
                } else {
                    builder.append(count).append(temp);
                    temp = c;
                    count = 1;
                }
            }
            dp[i] = builder.append(count).append(temp).toString();
        }
        return dp[n];
    }

    public String countAndSay2(int n) {
        // dp[1]
        String pre = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            char temp = pre.charAt(0);
            int count = 0;
            for (char c : pre.toCharArray()) {
                if (Objects.equals(c, temp)) {
                    count++;
                } else {
                    builder.append(count).append(temp);
                    temp = c;
                    count = 1;
                }
            }
            pre = builder.append(count).append(temp).toString();
        }
        return pre;
    }

}
