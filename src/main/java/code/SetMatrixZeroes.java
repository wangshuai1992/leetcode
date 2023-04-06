package code;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-26 02:43
 */
public class SetMatrixZeroes {

    /**
     * O(MN(M+N)) time, O(MN) space
     * 新开一个矩阵用于记录为0的位置
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean[][] tempM = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    // 标记应该为0的位置
                    for (int a = 0; a < tempM.length; a++) {
                        tempM[a][j] = true;
                    }
                    for (int b = 0; b < tempM[i].length; b++) {
                        tempM[i][b] = true;
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (tempM[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * O(MN) time, O(M + N) space
     * 用两个数组记录应该为0的一维下标和二维下标
     *
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        boolean[] xs = new boolean[matrix.length];
        boolean[] ys = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    // 标记应该为0的位置
                    xs[i] = true;
                    ys[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (xs[i] || ys[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * O(MN) time, O(1) space
     *
     * 使用两个标记变量
     *
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间。但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含0。
     * 因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含0。
     * 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
     *
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagCol0 = false;
        boolean flagRow0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * O(MN) time, O(1) space
     *
     * 使用一个标记变量
     *
     * 进一步优化，只使用一个标记变量记录第一列是否原本存在0。这样，第一列的第一个元素即可以标记第一行是否出现0。
     * 但为了防止每一列的第一个元素被提前更新，我们需要从最后一行开始，倒序地处理矩阵元素。
     *
     * @param matrix
     */
    public void setZeroes3(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }
}
