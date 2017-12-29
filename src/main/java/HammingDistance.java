/**
 * https://leetcode.com/problems/hamming-distance/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-29 15:01
 */
public class HammingDistance {

    /**
    public int hammingDistance(int x, int y) {
        StringBuilder xStr = new StringBuilder(Integer.toBinaryString(x));
        StringBuilder yStr = new StringBuilder(Integer.toBinaryString(y));

        int xLen = xStr.length();
        int yLen = yStr.length();

        int compareLen;

        if (xLen > yLen) {
            compareLen = xLen;
            for (int i = 0; i < xLen - yLen; i++) {
                yStr.insert(0, '0');
            }
        } else {
            compareLen = yLen;
            for (int i = 0; i < yLen - xLen; i++) {
                xStr.insert(0, '0');
            }
        }

        char[] xArr = xStr.toString().toCharArray();
        char[] yArr = yStr.toString().toCharArray();

        int result = 0;
        for (int i = 0; i < compareLen; i++) {
            if (xArr[i] != yArr[i]) {
                result++;
            }
        }

        return result;

    }
     **/

    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        System.out.println(new HammingDistance().hammingDistance(4, 14));
    }
}
