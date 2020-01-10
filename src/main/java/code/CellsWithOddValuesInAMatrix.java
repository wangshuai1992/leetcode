package code;

/**
 * https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 15:41
 */
public class CellsWithOddValuesInAMatrix {

    public int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        for (int[] indice : indices) {
            for (int col = 0; col < m; col++) {
                matrix[indice[0]][col]++;
            }
            for (int row = 0; row < n; row++) {
                matrix[row][indice[1]]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((matrix[i][j] & 1) == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
