package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-29 17:59
 */
public class ValidAnagram {

    /**
     * sort and compare / O(nlogn) time
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        return Arrays.equals(chars, chars1);
    }

    /**
     * count map / O(n) time
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a'] ++;
        }

        for(char c : t.toCharArray()) {
            if(--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
