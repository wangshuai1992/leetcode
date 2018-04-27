package code;

/**
 * https://leetcode.com/problems/palindromic-substrings/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-20 11:30
 */
public class PalindromicSubstrings {

    /*
    public int countSubstrings(String s) {
        int result = 0;
        int len = s.length();
        for(int i=0; i<len; i++) {
            for(int j=i; j<len; j++) {
                String sub = s.substring(i, j+1);
                if(isPalindrome(sub)) {
                    result ++;
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
    */

    /**
     * Expand Around Center
     *
     * Let N be the length of the string. The middle of the palindrome could be in one of 2N - 1 positions: either
     * at letter or between two letters.
     *
     * For each center, let's count all the palindromes that have this center. Notice that if [a, b] is a
     * palindromic interval (meaning S[a], S[a+1], ..., S[b] is a palindrome), then [a+1, b-1] is one too.
     *
     * For each possible palindrome center, let's expand our candidate palindrome on the interval [left, right] as
     * long as we can. The condition for expanding is left >= 0 and right < N and S[left] == S[right]. That means we
     * want to count a new palindrome S[left], S[left+1], ..., S[right].
     *
     * @param S
     * @return
     */
    public int countSubstrings(String S) {
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2 * N - 1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

}
