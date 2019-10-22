package code;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-11 17:49
 */
public class WordBreak {

    /**
     * Recursive
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return recurse(s, 0, wordDict);
    }

    private boolean recurse(String s, int start, List<String> wordDict) {
        String testStr = s.substring(start);
        if (start == s.length()) {
            return true;
        }
        for (String dict : wordDict) {
            if (testStr.startsWith(dict) && recurse(testStr, dict.length(), wordDict)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recursive
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        // set contains method takes O(1), ArrayList takes O(n)
        Set<String> dictSet = new HashSet<>(wordDict);
        return recurse1(s, 0, dictSet);
    }

    private boolean recurse1(String s, int start, Set<String> wordSet) {
        if (start == s.length()) {
            return true;
        }
        for(int i = start; i < s.length(); i++) {
            if(wordSet.contains(s.substring(start, i + 1)) && recurse1(s, i + 1, wordSet)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recursive with memorization
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        // set contains method takes O(1), ArrayList takes O(n)
        Set<String> dictSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length() + 1];
        return recurseWithMemo(s, 0, dictSet, memo);
    }

    private boolean recurseWithMemo(String s, int start, Set<String> wordSet, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if(memo[start] != null) {
            return memo[start];
        }
        for(int i = start; i < s.length(); i++) {
            if(wordSet.contains(s.substring(start, i + 1)) && recurseWithMemo(s, i + 1, wordSet, memo)) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }

    /**
     * dp / O(n^3) time
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        // set contains method takes O(1), ArrayList takes O(n)
        Set<String> dictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        for(int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                if(dp[j] && dictSet.contains(s.substring(i, j))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "applepenapple";
        List<String> dict = Arrays.asList("apple", "pen");
        System.out.println(new WordBreak().wordBreak(str, dict));
    }

}
