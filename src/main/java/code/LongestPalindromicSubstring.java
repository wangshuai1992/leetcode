package code;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-10 10:18
 */
public class LongestPalindromicSubstring {

    /**
     * recursive
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        int start = 0;
        int end = 0;
        int maxLen = -1;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (recurse(chars, i, j) && (maxLen < j - i + 1)) {
                    maxLen = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private boolean recurse(char[] chars, int start, int end) {
        if (end - start <= 0) {
            return true;
        }
        return chars[start] == chars[end] && (end - start < 3 || recurse(chars, start + 1, end - 1));
    }

    /**
     * dp / O(n^2) time, O(n^2) space
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        boolean[][] isPalindrome = new boolean[chars.length][chars.length];
        int start = 0;
        int end = 0;
        int maxLen = -1;
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                if (j - i < 3) {
                    isPalindrome[i][j] = chars[i] == chars[j];
                } else {
                    isPalindrome[i][j] = chars[i] == chars[j] && isPalindrome[i + 1][j - 1];
                }
                if (isPalindrome[i][j]) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }


    /**
     * expand from every center  / O(n^2) time  O(1) space
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        // 结果字符串的下标
        int start = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            int len1 = expandAroundCenter(chars, i, i);
            int len2 = expandAroundCenter(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 返回从指定下标开始扩展的最长回文子串的长度
     *
     * @param chars
     * @param left
     * @param right
     * @return
     */
    private int expandAroundCenter(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * Manacher's Algorithm
     * https://www.zhihu.com/question/37289584/answer/465656849
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        // TODO
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int iMirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[iMirror]);// 防止超出 R
            } else {
                P[i] = 0;// 等于 R 的情况
            }

            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // 判断是否需要更新 R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
        return s.substring(start, start + maxLen);
    }

    private String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        StringBuilder ret = new StringBuilder("^");
        for (int i = 0; i < n; i++) {
            ret.append("#").append(s.charAt(i));
        }
        ret.append("#$");
        return ret.toString();
    }

}
