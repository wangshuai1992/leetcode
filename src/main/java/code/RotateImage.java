package code;

/**
 * https://leetcode.com/problems/rotate-image/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-13 14:43
 */
public class RotateImage {

    /**
     * matrix[i][j] => matrix[j][len - 1 - i]
     *
     * O(n^2) time, O(n^2) space
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int[][] temp = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[j][len - 1 - i] = matrix[i][j];
            }
        }

        for (int i = 0; i < len; i++) {
            System.arraycopy(temp[i], 0, matrix[i], 0, len);
        }
    }

    /**
     * general idea to rotate a matrix
     *
     * clockwise rotate:
     *
     * 1.reverse up to down  (x, y) => (len-1-x, y)
     * 2.swap the symmetry   (len-1-x, y) => (y, len-1-x)
     *
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     *
     *
     * anti-clockwise rotate:
     *
     * 1.transpose (x, y) => (y, x)
     * 2.reverse up to down => (len-1-y, x)
     *
     * @param matrix
     */
    public void rotate1(int[][] matrix) {
        for(int i = 0; i < matrix.length / 2; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = temp;
            }
        }
        for(int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * recurse from outter to inner
     *
     * O(n^2) time, O(1) space
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        change(matrix, 0, matrix.length - 1, 0);
    }

    private void change(int[][] matrix, int start, int end, int row) {
        if (start >= end) {
            return;
        }

        int n = matrix.length - 1;
        //four points => 1(i, row) - 2(row, n-i) - 3(n-i, n-row) - 4(n-row, i)
        for (int i = start; i < end; i++) {
            // temp <= p2
            int temp = matrix[row][n - i];
            // p2 <= p1
            matrix[row][n - i] = matrix[i][row];
            // p1 <= p4
            matrix[i][row] = matrix[n - row][i];
            // p4 <= p3
            matrix[n - row][i] = matrix[n - i][n - row];
            // p3 <= temp
            matrix[n - i][n - row] = temp;
        }
        change(matrix, start + 1, end - 1, row + 1);
    }

    /**
     * iterate from outter to inner
     *
     * O(n^2) time, O(1) space
     *
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length - 1;
        int start = 0;
        int end = n;
        int row = 0;
        while (start < end) {
            //four points => 1(i, row) - 2(row, n-i) - 3(n-i, n-row) - 4(n-row, i)
            for (int i = start; i < end; i++) {
                // temp <= p2
                int temp = matrix[row][n - i];
                // p2 <= p1
                matrix[row][n - i] = matrix[i][row];
                // p1 <= p4
                matrix[i][row] = matrix[n - row][i];
                // p4 <= p3
                matrix[n - row][i] = matrix[n - i][n - row];
                // p3 <= temp
                matrix[n - i][n - row] = temp;
            }
            start++;
            end--;
            row++;
        }
    }

}
