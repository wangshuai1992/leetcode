package code;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-06 17:44
 */
public class MergeSortedArray {

    /**
     * two pointer
     * O((m + n)x2) time, O(m + n) space
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int x = 0;
        int y = 0;
        int[] ans = new int[m + n];
        while (x < m || y < n) {
            if (x >= m) {
                ans[x + y] = nums2[y];
                y++;
            } else if (y >= n) {
                ans[x + y] = nums1[x];
                x++;
            } else if (nums1[x] <= nums2[y]) {
                ans[x + y] = nums1[x];
                x++;
            } else {
                ans[x + y] = nums2[y];
                y++;
            }
        }
        System.arraycopy(ans, 0, nums1, 0, m + n);
    }

    /**
     * 从后向前双指针遍历
     * O(m + n) time, O(1) space
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int x = m - 1;
        int y = n - 1;
        int index = m + n - 1;
        while (x >= 0 || y >= 0) {
            if (x < 0) {
                nums1[index] = nums2[y];
                y--;
            } else if (y < 0) {
                nums1[index] = nums1[x];
                x--;
            } else if (nums1[x] >= nums2[y]) {
                nums1[index] = nums1[x];
                x--;
            } else {
                nums1[index] = nums2[y];
                y--;
            }
            index--;
        }
    }

}
