package code;

/**
 * https://leetcode.com/problems/next-permutation/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-09 01:08
 */
public class NextPermutation {

    /**
     * https://leetcode.cn/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
     * O(n) time, O(1) space
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        for (int i = nums.length - 1; i > 0; i--) {
            // 从后向前找到第一个升序对
            int j = i - 1;
            if (nums[j] < nums[i]) {
                // 从末尾开始向前找到第一个比j位置大的
                for (int k = nums.length - 1; k >= i; k--) {
                    if (nums[k] > nums[j]) {
                        // 交换k位置与j位置的数
                        swap(nums, k, j);
                        break;
                    }
                }
                // 从i往后到末尾必然是降序的 反转为升序
                reverse(nums, i, nums.length);
                // 处理完成直接结束方法
                return;
            }
        }
        // 如果走到这里 说明整个数组是降序排列 直接reverse返回
        reverse(nums, 0, nums.length);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        for (int i = start, count = 0; i < (start + end) / 2; i++, count++) {
            swap(nums, i, end - count - 1);
        }
    }
}
