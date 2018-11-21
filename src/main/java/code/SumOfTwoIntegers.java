package code;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-21 17:17
 */
public class SumOfTwoIntegers {

    /**
     * 以3 + 5为例
     * 3的二进制为 1 1，5的二进制为 1 0 1，可以这样做：
     *
     * 1. 先给这两个数加起来不考虑进位，这样得到的结果为 1 1 0，会发现与^得到的结果相同，与是先给两个数做^运算；
     *
     * 2. 接下来考虑进位，两个二进制数相加会有这么几种情况 1 1,0 0, 1 0, 0 1除第一种情况外其他情况均不产生进位，而1 1两数相加进1，
     * 结果得0，可以这样做先将两个数做&运算，再将结果左移1位，这样就模拟了进位 
     *
     * 3、将第1步得到的没进位的和 和第2步的进位相加（递归）便是结果
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if(b == 0) {
            return a;
        }

        //不考虑进位的和
        int sum = a ^ b;

        //模拟进位(对于某一位来说，只有同时为1才产生进位)
        int carry = (a & b) << 1;

        return getSum(sum, carry);
    }

}
