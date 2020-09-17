package code;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-17 15:34
 */
public class StringToIntegerAtoi {

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        // trim
        int start = 0;
        int end = str.length() - 1;
        int len = chars.length;
        while (start < len && chars[start] == ' ') {
            start++;
        }
        if (start == len) {
            return 0;
        }
        while (end > start && str.charAt(end) == ' ') {
            end--;
        }

        // initial plus or minus sign
        boolean isNeg = false;
        if (chars[start] == '-') {
            start ++;
            isNeg = true;
        } else if (chars[start] == '+') {
            start ++;
        }

        // deal nums
        int res = 0;
        while (start <= end) {
            char c = chars[start];
            if (c < '0' || c > '9') {
                break;
            }
            int num = c - '0';
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > 7)) {
                // 溢出
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + num;
            start++;
        }
        return isNeg ? -res : res;
    }

    public static void main(String[] args) {
        // true
        System.out.println(Integer.MAX_VALUE == -(Integer.MIN_VALUE + 1));
    }
}
