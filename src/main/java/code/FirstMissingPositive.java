package code;

/**
 * https://leetcode.com/problems/first-missing-positive/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-19 03:01
 */
public class FirstMissingPositive {

    /**
     * https://leetcode.cn/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
     * 对于一个长度为N的数组，其中没有出现的最小正整数只能在[1,N+1] 中
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 将负数变为n + 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        // 将<=n的元素值当成下标，把对应数组元素变为负数
        for (int i = 0; i < nums.length; i++) {
            // 这里可能在0, i - 1的循环过程中已经变成了负数
            int temp = Math.abs(nums[i]);
            if (temp <= n) {
                nums[temp - 1] = -Math.abs(nums[temp - 1]);
            }
        }
        // 返回第一个大于0的元素的下标 + 1
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 置换方式
     * 将给定的数组「恢复」成下面的形式：
     * 如果数组中包含x在区间[1,N]中，那么恢复后，数组的第x-1个元素为x。
     *
     * 在恢复后，数组应当有 [1, 2, ..., N] 的形式，但其中有若干个位置上的数是错误的，每一个错误的位置就代表了一个缺失的正数
     *
     * 由于每次的交换操作都会使得某一个数交换到正确的位置，因此交换的次数最多为N
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int j = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

}
