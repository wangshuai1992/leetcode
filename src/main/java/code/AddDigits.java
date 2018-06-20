package code;

/**
 * https://leetcode.com/problems/add-digits/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 14:21
 */
public class AddDigits {

    /**
     * https://en.wikipedia.org/wiki/Digital_root
     * https://zh.wikipedia.org/wiki/%E6%95%B8%E6%A0%B9
     *
     * [Step 1]:
     *
     * 438  == 40*10 +3*10 +8 ;
     * 4+3+8 == 4*(10%9)*(10%9) + 3*(10%9) + 8%9 = 15 ;
     *
     * [Step 2]:
     *
     * 15  == 1*10 + 5 ;
     * 1+5 == 1*(10%9) + 5%9 = 6 ;
     *
     * [So we can see]:
     *
     * ab%9%9%9 == ab%9;
     *
     * just return num%9; and don't forget num==0 or num==9
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 9 == 0 ? 9 : num % 9;
    }

}
