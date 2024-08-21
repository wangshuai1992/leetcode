package code;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-16 10:39
 */
@SuppressWarnings("DataFlowIssue")
public class KthLargestElementInAnArray {

    /**
     * min-heap / O(NlgK) running time + O(K) memory
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>(k + 1);
        for (int n : nums) {
            minHeap.offer(n);
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
        // 类似二分查找
        while (low <= high) {
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

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        // 关于区间的边界控制需格外小心，稍有不慎就会出错
        // 这里把 i, j 定义为开区间，同时定义：
        // [lo, i) <= pivot
        // (j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = low + 1;
        int j = high;
        // 因为[i, j]是闭区间 所以相等时候也要进入while循环
        while (i <= j) {
            // 从左开始找大于pivot的数
            while(i < high && nums[i] <= pivot) {
                i++;
            }
            // 从右开始找小于等于pivot的数
            while(j > low && nums[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
