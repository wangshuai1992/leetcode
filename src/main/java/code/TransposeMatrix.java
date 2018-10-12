package code;

/**
 * https://leetcode.com/problems/transpose-matrix/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-10 16:34
 */
public class TransposeMatrix {

    public int[][] transpose(int[][] arr) {
        int rowLen = arr.length;
        int colLen = arr[0].length;

        int[][] result = new int[colLen][rowLen];

        for(int i=0; i<rowLen; i++) {
            for(int j=0; j<colLen; j++) {
                result[j][i] = arr[i][j];
            }
        }
        return result;
    }

}
