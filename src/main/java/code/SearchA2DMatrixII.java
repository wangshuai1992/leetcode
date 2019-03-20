package code;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-07 17:02
 */
public class SearchA2DMatrixII {

    /**
     * We start search the matrix from top right corner, initialize the current position to top right corner,
     * if the target is greater than the value in current position, then the target can not be in entire row
     * of current position because the row is sorted, if the target is less than the value in current position,
     * then the target can not in the entire column because the column is sorted too. We can rule out one row or
     * one column each time, so the time complexity is O(m+n).
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int curX = 0;
        int curY = matrix[0].length - 1;
        while (curX < matrix.length && curY >= 0) {
            int val = matrix[curX][curY];
            if (val == target) {
                return true;
            }
            if (val < target) {
                curX++;
            }
            if (val > target) {
                curY--;
            }
        }
        return false;
    }

}
