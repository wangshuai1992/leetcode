package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/toeplitz-matrix/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-10 14:18
 */
public class ToeplitzMatrix {

    /*
    public boolean isToeplitzMatrix(int[][] matrix) {
        int lenth = matrix[0].length;
        int width = matrix.length;

        for(int i=0; i<width; i++) {
            int val = matrix[i][0];
            for(int m=i+1, n=1; m<width && n<lenth; m++, n++) {
                if(matrix[m][n] != val) {
                    return false;
                }
            }
        }

        for(int j=1; j<lenth; j++) {
            int val = matrix[0][j];
            for(int m=1, n=j+1; m<width && n<lenth; m++, n++) {
                if(matrix[m][n] != val) {
                    return false;
                }
            }
        }
        return true;
    }
    */

    /**
     * We ask what feature makes two coordinates (r1, c1) and (r2, c2) belong to the same diagonal?
     * It turns out two coordinates are on the same diagonal if and only if r1 - c1 == r2 - c2.
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer> groups = new HashMap<>();
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                if (!groups.containsKey(r - c))
                    groups.put(r - c, matrix[r][c]);
                else if (groups.get(r - c) != matrix[r][c])
                    return false;
            }
        }
        return true;
    }

}
