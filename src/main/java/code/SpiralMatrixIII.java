package code;

/**
 * https://leetcode.com/problems/spiral-matrix-iii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-10 17:21
 */
public class SpiralMatrixIII {

    /**
     * Examining the lengths of our walk in each direction, we find the following pattern: 1, 1, 2, 2, 3, 3, 4, 4, ...
     * <p>
     * That is, we walk 1 unit east, then 1 unit south, then 2 units west, then 2 units north, then 3 units east, etc.
     * <p>
     * Because our walk is self-similar, this pattern repeats in the way we expect.
     *
     * @param r
     * @param c
     * @param r0
     * @param c0
     * @return
     */
    public int[][] spiralMatrixIII(int r, int c, int r0, int c0) {
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        int[][] ans = new int[r * c][2];
        int t = 0;

        ans[t++] = new int[]{r0, c0};
        if (r * c == 1) {
            return ans;
        }

        //一圈之后每个方向的长度增加2
        for (int k = 1; k < 2 * (r + c); k += 2) {
            // i: direction index
            for (int i = 0; i < 4; ++i) {
                // number of steps in this direction
                int dk = k + (i / 2);
                // for each step in this direction...
                for (int j = 0; j < dk; ++j) {
                    // step in the i-th direction
                    r0 += dr[i];
                    c0 += dc[i];
                    if (0 <= r0 && r0 < r && 0 <= c0 && c0 < c) {
                        ans[t++] = new int[]{r0, c0};
                        if (t == r * c) {
                            return ans;
                        }
                    }
                }
            }
        }

        return null;
    }

}
