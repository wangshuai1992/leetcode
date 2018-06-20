package code;

/**
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 15:18
 */
public class MoveZeroes {

    /**
     * 冒泡
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        for(int i=0; i<nums.length - 1; i++) {
            for(int j=0; j<nums.length - i - 1; j++) {
                if(nums[j] == 0) {
                    swap(nums, j, j+1);
                }
            }
        }
    }

    /**
     * 记录上一个非零数字的位置
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        int lastNonZeroFoundAt = -1;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                //每发现一个非零数 数组头部已知的的非零数+1
                lastNonZeroFoundAt ++;

                swap(nums, lastNonZeroFoundAt, cur);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
