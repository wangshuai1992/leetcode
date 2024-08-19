package code;

/**
 * https://leetcode.com/problems/valid-palindrome/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-14 22:59
 */
public class ValidPalindrome {

    /**
     * O(N) time, O(N) space
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] chars = new char[s.length()];
        int count = 0;
        for (char c : s.toCharArray()) {
            // 大写转小写
            if (c >= 'A' && c <= 'Z') {
                c += ('a' - 'A');
            }
            // 字母或数字
            if (c >= 'a' && c <= 'z' || (c >= '0' && c <= '9')) {
                chars[count++] = c;
            }
        }
        int i = 0;
        int j = count - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * O(N) time, O(1) space
     *
     * @param s
     * @return
     */
    public boolean isPalindrome1(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < s.length() && i < j && !isValid(s.charAt(i))) {
                i++;
            }
            while (j >= 0 && i < j && !isValid(s.charAt(j))) {
                j--;
            }
            if (i >= j) {
                break;
            }
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (a >= 'A' && a <= 'Z') {
                a += ('a' - 'A');
            }
            if (b >= 'A' && b <= 'Z') {
                b += ('a' - 'A');
            }
            if (a != b) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isValid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public static void main(String[] args) {
        new ValidPalindrome().isPalindrome("0P");
    }

}
