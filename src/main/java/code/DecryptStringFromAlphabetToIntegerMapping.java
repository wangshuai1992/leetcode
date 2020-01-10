package code;

/**
 * https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 15:56
 */
public class DecryptStringFromAlphabetToIntegerMapping {

    /**
     * traverse from front to end
     *
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        StringBuilder builder = new StringBuilder();
        char pre2 = ' ';
        char pre1 = ' ';
        for (char c : s.toCharArray()) {
            if (pre2 == ' ') {
                pre2 = pre1;
                pre1 = c;
                continue;
            }
            if (Character.isDigit(c)) {
                builder.append(getChar(pre2));
                pre2 = pre1;
                pre1 = c;
            } else {
                builder.append(getChar(pre2, pre1));
                pre2 = ' ';
                pre1 = ' ';
            }
        }
        if (pre2 != ' ') {
            builder.append(getChar(pre2));
        }
        if (pre1 != ' ') {
            builder.append(getChar(pre1));
        }
        return builder.toString();
    }

    /**
     * traverse from end to front
     *
     * @param s
     * @return
     */
    public String freqAlphabets1(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                builder.insert(0, getChar(c));
            } else {
                builder.insert(0, getChar(s.charAt(i - 2), s.charAt(i - 1)));
                i -= 2;
            }
        }
        return builder.toString();
    }

    private char getChar(char ch) {
        char baseChar = 'a' - 1;
        return (char) (baseChar + (ch - '0'));
    }

    private char getChar(char ch2, char ch1) {
        char baseChar = 'a' - 1;
        return (char) (baseChar + (ch2 - '0') * 10 + (ch1 - '0'));
    }

}
