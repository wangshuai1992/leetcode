package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-03 14:46
 */
public class NQueens {

    /**
     * brute force backtracking
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] nqueens = new int[n];
        helper(res, nqueens, n, 0);
        return res;
    }

    private void helper(List<List<String>> result, int[] nqueens, int n, int i) {
        if (i == nqueens.length) {
            List<String> one = new LinkedList<>();
            // 构成表示整个棋盘的字符串
            for (int num : nqueens) {
                // 构成一个形如....Q....的字符串
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    sb.append('.');
                }
                sb.append('Q');
                for (int j = num + 1; j < n; j++) {
                    sb.append('.');
                }
                one.add(sb.toString());
            }
            result.add(one);
        } else {
            //选择下一列的数字
            // 比如之前已经选了13xxxxxx，下一列可以选6，形成136xxxxx
            for (int num = 0; num < n; num++) {
                nqueens[i] = num;
                // 如果是有效的，继续搜索
                if (isValid(nqueens, i)) {
                    helper(result, nqueens, n, i + 1);
                }
            }
        }
    }

    private boolean isValid(int[] nqueens, int i) {
        for (int idx = 0; idx < i; idx++) {
            // 检查对角线只要判断他们差的绝对值和坐标的差是否相等就行了
            if (nqueens[idx] == nqueens[i] || Math.abs(nqueens[idx] - nqueens[i]) == i - idx) {
                return false;
            }
        }
        return true;
    }

    /**
     * DFS / Backtracking use visti array
     *
     * 我们用三个数组来保存之前皇后的信息，就可以O(1)时间判断出皇后是否冲突，而不需要遍历之前皇后的摆放位置
     *
     * vis[0] using to judge whether they are on the same col
     * vis[1] using to judge whether they are on the same counter-diagonal
     * vis[2] using to judge whether they are on the same main-diagonal
     *
     * If two queens Q1&Q2 are on the same main-diagonal:
     * Q1.row-Q1.col == Q2.row-Q2.col or Q1.col-Q1.row == Q2.col-Q2.row
     *
     * If two queens are on the same counter-diagonal:
     * Q1.row+Q1.col == Q2.row+Q2.col
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> result = new ArrayList<>();
        int[][] visit = new int[3][2 * n];
        dfs(result, 0, n, new int[n], visit);
        return result;
    }

    /**
     * @param result result
     * @param cur  current row. begin with 0
     * @param n    N queens
     * @param qPos pos = qPos[i], pos is the column of queen in row i
     * @param visit visit[][]
     */
    private void dfs(List<List<String>> result, int cur, int n, int[] qPos, int[][] visit) {
        //find the solution
        if (cur == n) {
            List<String> s = new ArrayList<>();
            int row = 0;
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int col = 0; col < n; col++) {
                    if (col == qPos[row]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                s.add(sb.toString());
                row++;
            }
            result.add(s);
            return;
        }

        // for each row, test all the cols
        for (int i = 0; i < n; i++) {
            // there is no violation
            if ((visit[0][i] == 0) && (visit[1][cur + i] == 0) && (visit[2][cur - i + n] == 0)) {
                // choose the col i
                qPos[cur] = i;

                // set the same col & the counter-diagonal & the main-diagonal of i
                // notice that the main-diagonal(col-row) could <0 so we add n
                visit[0][i] = visit[1][cur + i] = visit[2][cur - i + n] = 1;

                // backtracking
                dfs(result, cur + 1, n, qPos, visit);
                visit[0][i] = visit[1][cur + i] = visit[2][cur - i + n] = 0;

            }
        }
    }

}
