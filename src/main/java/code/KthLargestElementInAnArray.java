package code;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-16 10:39
 */
public class KthLargestElementInAnArray {

    /**
     * min-heap / O(NlgK) running time + O(K) memory
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int n : nums) {
            if (minHeap.size() < k || minHeap.peek() < n) {
                minHeap.offer(n);
            }
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * use quick select / O(N) best case / O(N^2) worst case running time + O(1) memory
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {

        k = nums.length - k;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int j = partition(nums, low, high);
            if (j < k) {
                low = j + 1;
            } else if (j > k) {
                high = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int low, int high) {
        //将a[low]的值作为pivot
        int pivot = a[low];
        while (low < high) {
            while (low < high && a[high] >= pivot) {
                --high;
            }
            //交换比pivot小的记录到左端
            a[low] = a[high];
            while (low < high && a[low] <= pivot) {
                ++low;
            }
            //交换比pivot小的记录到右端
            a[high] = a[low];
        }
        //扫描完成，pivot到位
        a[low] = pivot;
        //返回的是pivot的位置
        return low;
    }

}
