package code;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-10-22 16:58
 */
public class SquaresOfASortedArray {

    /**
     * Two Pointers
     *
     * O(n) time, O(1) space
     *
     * @param arr
     * @return
     */
    public int[] sortedSquares(int[] arr) {
        int firstPositiveIndex = arr.length;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                firstPositiveIndex = i;
                break;
            }
        }

        int[] result = new int[arr.length];
        int pIndex = firstPositiveIndex;
        int nIndex = firstPositiveIndex - 1;
        int newIndex = 0;
        while (newIndex < arr.length) {
            int pValue = pIndex < arr.length ? arr[pIndex] : Integer.MAX_VALUE;
            int nValue = nIndex >= 0 ? -arr[nIndex] : Integer.MAX_VALUE;
            if (nValue < pValue) {
                nIndex--;
                result[newIndex] = nValue * nValue;
            } else {
                pIndex++;
                result[newIndex] = pValue * pValue;
            }
            newIndex++;
        }
        return result;
    }

}
