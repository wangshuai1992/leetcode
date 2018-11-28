package code;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 15:19
 */
public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        int len = s.length();
        int result = 0;
        for (int i = len - 1; i >= 0; i--) {
            int factor = (int) Math.pow(26, len - 1 - i);
            result += getVal(s.charAt(i)) * factor;
        }
        return result;
    }

    private int getVal(char c) {
        return c - 'A' + 1;
    }

}
