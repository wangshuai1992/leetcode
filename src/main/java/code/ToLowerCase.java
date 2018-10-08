package code;

/**
 * https://leetcode.com/problems/to-lower-case/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-07-16 10:40
 */
public class ToLowerCase {

    public String toLowerCase(String str) {
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }

    public String toLowerCase1(String str) {
        int diff = 'A' - 'a';
        StringBuilder lower = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                lower.append((char) (c - diff));
            } else {
                lower.append(c);
            }
        }
        return lower.toString();
    }

}
