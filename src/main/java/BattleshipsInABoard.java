/**
 * https://leetcode.com/problems/battleships-in-a-board/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-30 15:20
 */
public class BattleshipsInABoard {

    /*
    public int countBattleships(char[][] board) {
        int num = 0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == 'X') {
                    num ++;
                    changeValue(board, i, j);
                }
            }
        }
        return num;
    }

    private void changeValue(char[][] board, int i, int j) {
        board[i][j] = '.';
        if(i + 1 < board.length && board[i+1][j] == 'X') {
            changeValue(board, i+1, j);
        }
        if(j + 1 < board[i].length && board[i][j+1] == 'X') {
            changeValue(board, i, j+1);
        }
    }
     */

    /**
     * Going over all cells, we can count only those that are the “first” cell of the battleship. First cell will be
     * defined as the most top-left cell. We can check for first cells by only counting cells that do not have an ‘X’
     * to the left and do not have an ‘X’ above them.
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int num = 0;

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == '.') {
                    continue;
                }
                if(i - 1 >= 0 && board[i-1][j] == 'X') {
                    continue;
                }
                if(j - 1 >= 0 && board[i][j-1] == 'X') {
                    continue;
                }
                num ++;
            }
        }
        return num;
    }

}
