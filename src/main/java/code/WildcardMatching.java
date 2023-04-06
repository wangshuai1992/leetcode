package code;

/**
 * https://leetcode.com/problems/wildcard-matching/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 12:17
 */
public class WildcardMatching {

    /**
     * backtracking
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        return match(s, p, 0, 0);
    }

    private boolean match(String s, String p, int sIndex, int pIndex) {
        if (sIndex >= s.length() && pIndex >= p.length()) {
            return true;
        }
        // s匹配完 p没匹配完
        if (sIndex >= s.length() && p.charAt(pIndex) != '*') {
            return false;
        }
        if (sIndex >= s.length() && p.charAt(pIndex) == '*') {
            return match(s, p, sIndex, pIndex + 1);
        }
        // s没匹配完 p匹配完
        if (sIndex < s.length() && pIndex >= p.length()) {
            return false;
        }
        if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?') {
            return match(s, p, sIndex + 1, pIndex + 1);
        } else if (p.charAt(pIndex) == '*') {
            // *代表空字符
            if (match(s, p, sIndex, pIndex + 1)) {
                return true;
            }
            // *至少代表一个字符
            for (int i = sIndex + 1; i <= s.length(); i++) {
                if (match(s, p, i, pIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * backtracking with memo
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return matchWithMemo(s, p, 0, 0, memo);
    }

    private boolean matchWithMemo(String s, String p, int sIndex, int pIndex, Boolean[][] memo) {
        if (sIndex >= s.length() && pIndex >= p.length()) {
            return true;
        }
        // s匹配完 p没匹配完
        if (sIndex >= s.length() && p.charAt(pIndex) != '*') {
            return false;
        }
        if (sIndex >= s.length() && p.charAt(pIndex) == '*') {
            if (memo[sIndex][pIndex + 1] == null) {
                memo[sIndex][pIndex + 1] = matchWithMemo(s, p, sIndex, pIndex + 1, memo);
            }
            return memo[sIndex][pIndex + 1];
        }
        // s没匹配完 p匹配完
        if (sIndex < s.length() && pIndex >= p.length()) {
            return false;
        }
        if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?') {
            if (memo[sIndex + 1][pIndex + 1] == null) {
                memo[sIndex + 1][pIndex + 1] = matchWithMemo(s, p, sIndex + 1, pIndex + 1, memo);
            }
            return memo[sIndex + 1][pIndex + 1];
        } else if (p.charAt(pIndex) == '*') {
            // *代表空字符
            if (memo[sIndex][pIndex + 1] == null) {
                memo[sIndex][pIndex + 1] = matchWithMemo(s, p, sIndex, pIndex + 1, memo);
            }
            if (memo[sIndex][pIndex + 1]) {
                return true;
            }
            // *至少代表一个字符
            for (int i = sIndex + 1; i <= s.length(); i++) {
                if (memo[i][pIndex] == null) {
                    memo[i][pIndex] = matchWithMemo(s, p, i, pIndex, memo);
                }
                if (memo[i][pIndex]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * dp
     * https://leetcode.cn/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 表示字符串s的前i个字符和模式p的前j个字符是否能匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 剩下的dp[i][0]都是默认的false

        // 如果p的前j个字符都为* 则这前j个字符都可以跟空串匹配
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            } else {
                // 注意这里是break
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // dp[i][j - 1]为true, 可以把当前这个*当做空字符 ==> dp[i][j]为true
                    // dp[i - 1][j]为true, 可以把当前*匹配任意一个字符
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 贪心
     * https://leetcode.cn/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch3(String s, String p) {
        int sRight = s.length();
        int pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }

        if (pRight == 0) {
            return sRight == 0;
        }
        int sIndex = 0;
        int pIndex = 0;
        int sRecord = -1;
        int pRecord = -1;
        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }
        return allStars(p, pIndex, pRight);
    }

    private boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    private boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().isMatch("zacabz", "*a?b*"));
    }

}
