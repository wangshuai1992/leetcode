package code;


/**
 * https://leetcode.com/problems/flipping-an-image/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-25 15:36
 */
public class FlippingAnImage {

    public int[][] flipAndInvertImage(int[][] arr) {
        for (int[] row : arr) {
            for (int j = 0; j < row.length / 2; j++) {
                int k = row.length - 1 - j;
                row[j] = row[j] ^ row[k];
                row[k] = row[j] ^ row[k];
                row[j] = row[j] ^ row[k];

                //invert
                row[j] ^= 1;
                row[k] ^= 1;
            }
            if ((row.length & 1) == 1) {
                int middle = row.length >> 1;
                row[middle] ^= 1;
            }
        }
        return arr;
    }

}
