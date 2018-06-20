package code;

/**
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 10:51
 */
public class PeakIndexInAMountainArray {

    public int peakIndexInMountainArray1(int[] a) {
        int i = 0;
        while (a[i] < a[i+1]) i++;
        return i;
    }

    public int peakIndexInMountainArray(int[] a) {
        int low = 0;
        int high = a.length - 1;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (a[middle] < a[middle + 1]) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

}
