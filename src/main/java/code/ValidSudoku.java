package code;

/**
 * https://leetcode.com/problems/valid-sudoku/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-01 23:58
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        //定义数字行内出现的次数
        int[][] row = new int[9][9];
        //定义数字列内出现的次数
        int[][] column = new int[9][9];
        //定义数字九宫格内出现的次数最大为9次
        int[][][] subbox = new int[3][3][9];
        //遍历数组
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '1';
                    row[i][index]++;
                    column[j][index]++;
                    // i/3 j/3定位到所在的小宫格
                    subbox[i / 3][j / 3][index]++;
                    //次数大于1就不成立一个数独
                    if (row[i][index] > 1 || column[j][index] > 1 || subbox[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
