package code;

import java.util.Random;

/**
 * https://leetcode.com/problems/sort-an-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-13 21:27
 */
public class SortAnArray {

    /**
     * 快排
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        shuffle(nums);
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int pIndex = partition(nums, low, high);
        quickSort(nums, low, pIndex - 1);
        quickSort(nums, pIndex + 1, high);
    }

    /**
     * @param nums
     * @param low
     * @param high
     * @return 分区过后pivot的下标
     */
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

    /**
     * 就地堆排
     * https://leetcode.cn/problems/sort-an-array/solution/pai-xu-shu-zu-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int[] sortArray1(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private void heapSort(int[] nums) {
        int len = nums.length - 1;
        // 构建大顶堆 从倒数第二层（至少有一个孩子节点的元素）开始 依次向上 每个节点执行下沉逻辑
        for (int i = len / 2; i >= 0; --i) {
            maxHeapify(nums, i, len);
        }
        // 依次把堆顶的最大元素交换到末尾 并让新堆顶元素下沉至[0, i - 1]的合适位置
        // 每一次循环保证范围内最大的一个数被交换到范围内的最后面 最终成为一个升序数组
        for (int i = len; i > 0; --i) {
            swap(nums, i, 0);
            // 下沉
            maxHeapify(nums, 0, i - 1);
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * 对下标i位置的元素执行下沉逻辑  只考虑[0, len]下标范围内的元素
     *
     * @param nums
     * @param i
     * @param len 数组下标上界
     */
    private void maxHeapify(int[] nums, int i, int len) {
        // 循环条件 左孩子节点在下标范围内
        while ((i * 2) + 1 <= len) {
            int leftSon = (i * 2) + 1;
            int rightSon = (i * 2) + 2;
            // 当前节点、左子节点、右子节点之中最大的节点的下标
            int max = i;
            if (leftSon <= len && nums[leftSon] > nums[max]) {
                max = leftSon;
            }
            if (rightSon <= len && nums[rightSon] > nums[max]) {
                max = rightSon;
            }
            if (max == i) {
                // 下沉操作完成
                break;
            }
            swap(nums, i, max);
            i = max;
        }
    }

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int[] sortArray2(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        // 合并两个有序数组
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int count = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[count++] = nums[i++];
            } else {
                tmp[count++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[count++] = nums[i++];
        }
        while (j <= right) {
            tmp[count++] = nums[j++];
        }
        // 从temp数组copy回原来的数组
        System.arraycopy(tmp, 0, nums, left, tmp.length);
    }

    private void shuffle(int[] nums) {
        Random r = new Random();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int j = i + r.nextInt(len - i);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
