package code;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-17 16:28
 */
public class RegularExpressionMatching {

    /**
     * recursive
     *
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    /**
     * recurse with memory
     *
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch1(String text, String pattern) {
        // TODO
        return false;
    }

}
