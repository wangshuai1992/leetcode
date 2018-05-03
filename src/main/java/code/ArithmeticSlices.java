package code;

/**
 * https://leetcode.com/problems/arithmetic-slices/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-27 16:23
 */
public class ArithmeticSlices {

    /*
    public int numberOfArithmeticSlices(int[] A) {
        int result = 0;
        for(int i=0; i<A.length; i++) {
            for(int j=i+2; j<A.length; j++) {
                if(A[j] - A[j-1] != A[j-1] - A[j-2]) {
                    break;
                }
                result ++;
            }
        }
        return result;
    }
    */

    private int sum = 0;

    public int numberOfArithmeticSlices(int[] A) {
        slices(A, A.length - 1);
        return sum;
    }

    /**
     * updates sum with the number of arithmetic slices(total) in the current range
     *
     * @param A
     * @param i
     * @return  the number of Arithmetic Slices in the range (k,i), but which are not a part of any
     *          range (k,j) such that j. k refers to the minimum index such that the range (k,i) constitutes
     *          a valid arithmetic slice.
     */
    public int slices(int[] A, int i) {
        if (i < 2) {
            return 0;
        }
        int ap = 0;
        if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
            ap = 1 + slices(A, i - 1);
            sum += ap;
        } else {
            slices(A, i - 1);
        }
        return ap;
    }


    /**
     *
     * @param A
     * @return
     */
    public int numberOfArithmeticSlicesDP(int[] A) {
        int dp = 0;
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else
                dp = 0;
        }
        return sum;
    }

}
