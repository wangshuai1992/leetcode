package code;

/**
 * https://leetcode.com/problems/score-after-flipping-matrix/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-07-16 10:44
 */
public class ScoreAfterFlippingMatrix {

    /**
     * brute force
     *
     * 先使用任意组合变换行(1<<row种组合)，再对列进行变换，列的变换只在增加1的数量时进行
     *
     * @param matrix
     * @return
     */
    public int matrixScore(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        //先算好初始状态下每列1的总数
        int[] colsums = new int[column];
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < column; ++c) {
                colsums[c] += matrix[r][c];
            }
        }

        int ans = 0;
        //对行变换 有1<<row种组合方式 二进制位为1代表进行变换 为0不变换
        for (int state = 0; state < (1 << row); ++state) {
            // Toggle the rows so that after, 'state' represents
            // the toggled rows.
            if (state > 0) {
                // 由于在之前的遍历中矩阵已经被改变，trans表示由上一个state到这一个state需要经过的行变换
                int trans = state ^ (state - 1);
                //分别对对应的行进行变换
                for (int r = 0; r < row; ++r) {
                    // ((trans >> r) & 1) 得到右起第r+1位的数字
                    if (((trans >> r) & 1) > 0) {
                        for (int c = 0; c < column; ++c) {
                            //改变每列1的数目
                            colsums[c] += matrix[r][c] == 1 ? -1 : 1;
                            //对应位置数字切换
                            matrix[r][c] ^= 1;
                        }
                    }
                }
            }

            // Calculate the score with the rows toggled by 'state'
            // 由于列变换只在增加1的数量的情况下进行，针对每种情况，列的变换是唯一的
            int score = 0;
            for (int c = 0; c < column; ++c) {
                // Math.max(colsums[c], row - colsums[c])代表列变换或者不变换两种情况下1较多时的数量
                score += Math.max(colsums[c], row - colsums[c]) * (1 << (column - 1 - c));
            }
            ans = Math.max(ans, score);
        }

        return ans;
    }

    public static void main(String[] args) {
        int row = 5;
        //5行有多少种行变换组合
        System.out.println(1 << row);

        //state代表一个特定的状态(最后一个)
        for(int state = 0; state < 1<<row; state++) {
            //状态的二进制表示
            System.out.println("====第" + (state+1) + "种变换====");
            if(state == 0) {
                System.out.println("state为0代表不变换");
                continue;
            }
            System.out.println("由上一个状态转换到这一个状态需要变换的位：" + Integer.toBinaryString(state ^ (state - 1)));
            System.out.println(Integer.toBinaryString(state));
        }

    }

    /**
     * 先行变换使得第一列全部为1， 这时各列与首列不同的位的数量与开始时相同
     *
     * @param matrix
     * @return
     */
    public int matrixScore1(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int ans = 0;
        for (int c = 0; c < colLen; ++c) {
            //变量col为每列开始时与第一列不同位的数量（最终结果要使得第一列全为1）
            int col = 0;
            for (int r = 0; r < rowLen; ++r) {
                col += matrix[r][c] ^ matrix[r][0];
            }
            ans += Math.max(col, rowLen - col) * (1 << (colLen-1-c));
        }
        return ans;
    }

}
