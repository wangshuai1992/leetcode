package code;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2021-04-14 10:35
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        if (matrix.length == 1 && matrix[0].length == 0) {
            return 0;
        }
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0] == '1' ? 1 : 0;
        }
        int xLen = matrix.length;
        int yLen = matrix[0].length;
        int result = 0;
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                int maxSize = 0;
                // 从i,j到m,n形成的长方形中是否含有0
                boolean[][] hasZero = new boolean[xLen][yLen];
                hasZero[i][j] = false;
                for (int m = i; m < xLen; m++) {
                    for (int n = j; n < yLen; n++) {
                        if (matrix[m][n] == '0' || (m > i && hasZero[m - 1][n]) || (n > j && hasZero[m][n - 1])) {
                            hasZero[m][n] = true;
                            break;
                        } else {
                            hasZero[m][n] = false;
                        }
                        int size = (m - i + 1) * (n - j + 1);
                        if (!hasZero[m][n] && size > maxSize) {
                            maxSize = size;
                        }
                    }
                }
                result = Math.max(result, maxSize);
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle1(char[][] matrix) {
        return 0;
    }

}
