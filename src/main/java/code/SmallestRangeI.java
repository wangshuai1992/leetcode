package code;

/**
 * https://leetcode.com/problems/smallest-range-i/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-10 16:47
 */
public class SmallestRangeI {

    public int smallestRangeI(int[] arr, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        int res = max - min - 2 * k;

        return res > 0 ? res : 0;
    }

}
