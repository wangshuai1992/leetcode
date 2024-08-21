package code;

/**
 * https://leetcode.com/problems/rotate-array/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-16 14:14
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        // k 可能大于数组长度
        k = k % len;

        reverse(nums, 0, len - 1 - k);
        reverse(nums, len - k, len - 1);
        reverse(nums, 0, len - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    /**
     * 最大公约数
     *
     * @param x
     * @param y
     * @return
     */
    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

}
