/**
 * https://leetcode.com/problems/counting-bits/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-30 15:58
 */
public class CountingBits {

    /*
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i=0; i<=num; i++) {
            result[i] = bitCount(i);
        }
        return result;
    }

    private int bitCount(int num) {
        if(num == 0) {
            return 0;
        }

        if(num == 1) {
            return 1;
        }

        int temp = num % 2;
        if(temp == 0) {
            return bitCount(num / 2);
        } else {
            return 1 + bitCount(num / 2);
        }
    }
     */

    public int[] countBits1(int num) {
        int[] answer = new int[num + 1];
        int offset = 1;
        for (int i = 1; i < answer.length; i++) {
            if (offset * 2 == i) offset *= 2;
            answer[i] = 1 + answer[i - offset];
        }
        return answer;
    }

    /**
     * Take number X for example, 10011001.
     * Divide it in 2 parts:
     * <1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
     * <2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
     *
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }

}
