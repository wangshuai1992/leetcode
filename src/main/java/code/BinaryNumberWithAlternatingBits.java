package code;

/**
 * https://leetcode.com/problems/binary-number-with-alternating-bits/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-17 14:46
 */
public class BinaryNumberWithAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        int temp = n & 1;
        while(n != 0) {
            n >>= 1;
            int lastBit = n & 1;
            if(lastBit == temp) {
                return false;
            }
            temp = lastBit;
        }
        return true;
    }

}
