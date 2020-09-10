package code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-09 14:44
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * recursive
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            res = Math.max(res, recurse(chars, i));
        }
        return res;
    }

    /**
     * 最长不重复子串的长度 该子串必须从start位置开始
     *
     * @param arr
     * @param start
     * @return
     */
    private int recurse(char[] arr, int start) {
        if (start >= arr.length) {
            return 0;
        }
        int step = recurse(arr, start + 1);
        char c = arr[start];
        for (int i = start + 1; i <= start + step && i < arr.length; i++) {
            if (c == arr[i]) {
                return i - start;
            }
        }
        return step + 1;
    }

    /**
     * recursive with memory
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        int[] memo = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            res = Math.max(res, recurse1(chars, i, memo));
        }
        return res;
    }

    /**
     * 最长不重复子串的长度 该子串必须从start位置开始
     *
     * @param arr
     * @param start
     * @return
     */
    private int recurse1(char[] arr, int start, int[] memo) {
        if (start >= arr.length) {
            return 0;
        }
        if (memo[start] != 0) {
            return memo[start];
        }
        int step = recurse1(arr, start + 1, memo);
        char c = arr[start];
        for (int i = start + 1; i <= start + step && i < arr.length; i++) {
            if (c == arr[i]) {
                memo[start] = i - start;
                return memo[start];
            }
        }
        memo[start] = step + 1;
        return memo[start];
    }

    /**
     * dp    O(n) - O(n^2) time
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        for (int i = chars.length - 1; i >= 0; i--) {
            int step = dp[i + 1];
            char c = chars[i];
            for (int j = i + 1; j <= i + step && j < chars.length; j++) {
                if (c == chars[j]) {
                    dp[i] = j - i;
                    break;
                }
            }
            if (dp[i] == 0) {
                dp[i] = step + 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * sliding window
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        Set<Character> windowSet = new HashSet<>(chars.length);
        int i = 0;
        int j = 0;
        while (i < chars.length && j < chars.length) {
            if (!windowSet.contains(chars[j])) {
                windowSet.add(chars[j]);
                j++;
                res = Math.max(res, j - i);
            } else {
                windowSet.remove(chars[i]);
                i++;
            }
        }
        return res;
    }

    /**
     * sliding window optimized
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring4(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        // value : index
        Map<Character, Integer> windowMap = new HashMap<>(chars.length);
        int i = 0;
        int j = 0;
        while (i < chars.length && j < chars.length) {
            if (windowMap.containsKey(chars[j])) {
                // 可能在前面已经被跳过了
                i = Math.max(windowMap.get(chars[j]) + 1, i);
            }
            windowMap.put(chars[j], j);
            j++;
            res = Math.max(res, j - i);
        }
        return res;
    }

}
