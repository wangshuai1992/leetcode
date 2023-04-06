package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-26 01:07
 */
public class SpiralMatrix {

    /**
     * recursive
     * 递归 每次缩小一圈
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        // 边界含头不含尾
        spiralOrder(matrix, 0, matrix[0].length, 0, matrix.length, result);
        return result;
    }

    private void spiralOrder(int[][] matrix, int c1, int c2, int r1, int r2, List<Integer> result) {
        if (c1 >= c2 || r1 >= r2) {
            return;
        }
        for (int i = c1; i < c2; i++) {
            result.add(matrix[r1][i]);
        }
        for (int i = r1 + 1; i < r2; i++) {
            result.add(matrix[i][c2 - 1]);
        }
        if (c1 != c2 - 1 && r1 != r2 - 1) {
            // 如果不是只有一行或者一列
            for (int i = c2 - 1 - 1; i >= c1; i--) {
                result.add(matrix[r2 - 1][i]);
            }
            for (int i = r2 - 1 - 1; i > r1; i--) {
                result.add(matrix[i][c1]);
            }
        }
        spiralOrder(matrix, c1 + 1, c2 - 1, r1 + 1, r2 - 1, result);
    }

    /**
     * 尾递归改迭代
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder1(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        int c1 = 0;
        int c2 = matrix[0].length;
        int r1 = 0;
        int r2 = matrix.length;
        List<Integer> result = new ArrayList<>();
        while (c1 < c2 && r1 < r2) {
            for (int i = c1; i < c2; i++) {
                result.add(matrix[r1][i]);
            }
            for (int i = r1 + 1; i < r2; i++) {
                result.add(matrix[i][c2 - 1]);
            }
            if (c1 != c2 - 1 && r1 != r2 - 1) {
                // 如果不是只有一行或者一列
                for (int i = c2 - 1 - 1; i >= c1; i--) {
                    result.add(matrix[r2 - 1][i]);
                }
                for (int i = r2 - 1 - 1; i > r1; i--) {
                    result.add(matrix[i][c1]);
                }
            }
            c1++;
            c2--;
            r1++;
            r2--;
        }
        return result;
    }

}
