package code;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-09 17:19
 */
public class MedianOfTwoSortedArrays {

    /**
     * O(n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        if (size1 == 0 && size2 == 0) {
            return 0;
        }
        if (size1 == 1 && size2 == 0) {
            return nums1[0];
        }
        if (size1 == 0 && size2 == 1) {
            return nums2[0];
        }
        int target = (size1 + size2) / 2;
        // 两数组长度和为奇数则取target那个数 ， 否则为target和target + 1数的平均值
        boolean isOdd = ((size1 + size2) & 1) == 1;
        int i = 0;
        int j = 0;
        int index = 0;
        double temp = 0;
        while (i < size1 || j < size2) {
            int n1 = i < nums1.length ? nums1[i] : Integer.MAX_VALUE;
            int n2 = j < nums2.length ? nums2[j] : Integer.MAX_VALUE;

            double num;
            if (n1 < n2) {
                num = n1;
                i++;
            } else {
                num = n2;
                j++;
            }
            if (!isOdd && index == target - 1) {
                temp = num;
            }
            if (!isOdd && index == target) {
                return (temp + num) / 2;
            }
            if (isOdd && index == target) {
                return num;
            }
            index++;
        }
        return 0;
    }

    /**
     * O(logn)
     *
     * https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
     * https://www.youtube.com/watch?v=LPFhl65R7ww
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // TODO
        return 0;
    }

}
