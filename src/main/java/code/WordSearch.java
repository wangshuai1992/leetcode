package code;

/**
 * https://leetcode.com/problems/word-search/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-28 01:36
 */
public class WordSearch {

    /**
     * backtrack
     * O(MN⋅3^L) time (由于剪枝的存在时间复杂度会远远低于这个值), O(MN) space, O(min(L,MN)) stack depth
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, visited, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, String word, int strIdx, int i, int j) {
        if (strIdx == word.length()) {
            // 成功找到
            return true;
        }
        // 越界、被访问过、当前位置的字符不是word对应位置的字符
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || visited[i][j] || board[i][j] != word.charAt(strIdx)) {
            return false;
        }
        visited[i][j] = true;
        if (dfs(board, visited, word, strIdx + 1, i + 1, j) ||
                dfs(board, visited, word, strIdx + 1, i - 1, j) ||
                dfs(board, visited, word, strIdx + 1, i, j + 1) ||
                dfs(board, visited, word, strIdx + 1, i, j - 1)) {
            return true;
        }
        // 走不通时, visited数组对应位置复位
        visited[i][j] = false;
        return false;
    }

}
