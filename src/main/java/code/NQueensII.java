package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/n-queens-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-03 14:46
 */
public class NQueensII {

    private final Set<Integer> occupiedCols = new HashSet<>();
    private final Set<Integer> occupiedDiag1s = new HashSet<>();
    private final Set<Integer> occupiedDiag2s = new HashSet<>();

    /**
     * don't need to actually place the queen,
     * instead, for each row, try to place without violation on
     * col/ diagonal1/ diagnol2.
     *
     * trick: to detect whether 2 positions sit on the same diagnol:
     * if delta(col, row) equals, same diagnol1;
     * if sum(col, row) equals, same diagnal2.
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        return totalNQueensHelper(0, 0, n);
    }

    private int totalNQueensHelper(int row, int count, int n) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col)) {
                continue;
            }
            int diag1 = row - col;
            if (occupiedDiag1s.contains(diag1)) {
                continue;
            }
            int diag2 = row + col;
            if (occupiedDiag2s.contains(diag2)) {
                continue;
            }
            // we can now place a queen here
            if (row == n - 1) {
                count++;
            } else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);

                count = totalNQueensHelper(row + 1, count, n);

                // recover
                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }
        return count;
    }

}
