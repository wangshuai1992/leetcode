package code;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-07 17:04
 */
public class SearchA2DMatrix {

    /**
     * binary search / row -> col
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        Integer row = searchRow(matrix, target, 0, matrix.length);
        return row != null && searchCol(matrix, target, row, 0, matrix[0].length);
    }

    private Integer searchRow(int[][] matrix, int target, int start, int end) {
        if (start + 1 == end) {
            return start;
        }
        int mid = (start + end) >> 1;
        if (matrix[start][0] <= target && matrix[mid - 1][matrix[0].length - 1] >= target) {
            return searchRow(matrix, target, start, mid);
        } else if (matrix[mid][0] <= target && matrix[end - 1][matrix[0].length - 1] >= target) {
            return searchRow(matrix, target, mid, end);
        } else {
            return null;
        }
    }

    private boolean searchCol(int[][] matrix, int target, int row, int start, int end) {
        if (start + 1 == end) {
            return matrix[row][start] == target;
        }
        int mid = (start + end) >> 1;
        if (matrix[row][start] <= target && matrix[row][mid - 1] >= target) {
            return searchCol(matrix, target, row, start, mid);
        } else {
            return searchCol(matrix, target, row, mid, end);
        }
    }

    /**
     * Treat matrix as a sorted list
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int left = 0;
        int right = row * col;
        while (left + 1 != right) {
            int mid = (left + right) >> 1;
            if (matrix[mid / col][mid % col] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return matrix[left / col][left % col] == target;
    }

}
