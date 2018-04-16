/**
 * https://leetcode.com/problems/reshape-the-matrix/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-13 10:07
 */
public class ReshapeTheMatrix {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(r * c != nums.length * nums[0].length) {
            return nums;
        }

        int[][] result = new int[r][c];

        int a=0;
        int b=0;
        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<nums[0].length; j++) {
                result[a][b] = nums[i][j];

                if(++b == c) {
                    b = 0;
                    a++;
                }
            }
        }
        return result;
    }

}
