package code;

/**
 * https://leetcode.com/problems/plus-one/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 16:16
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        // 进位
        int carry = 0;
        do {
            int i = digits[index];
            i++;
            if (i >= 10) {
                i = i - 10;
                carry++;
            } else {
                carry = 0;
            }
            digits[index] = i;
            index --;
        } while (carry > 0 && index >= 0);
        if (carry == 0) {
            return digits;
        }
        // 首位需要进位 说明原数组所有元素都是9
        int[] ans = new int[digits.length + 1];
        // 其他元素都是0
        ans[0] = 1;
        return ans;
    }

    public int[] plusOne1(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                // 加一后当前位不等于0即不需要进位
                return digits;
            }
        }
        // 首位需要进位 说明原数组所有元素都是9
        digits = new int[len + 1];
        // 其他元素都是0
        digits[0] = 1;
        return digits;
    }

}
