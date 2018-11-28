package code;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 16:35
 */
public class MinimumMovesToEqualArrayElements {

    public int minMoves1(int[] nums) {
        if(nums.length == 1) {
            return 0;
        }

        int result = 0;
        boolean isEqual = false;

        while (!isEqual) {
            int value = nums[0];
            for(int i=1; i<nums.length; i++) {
                if(nums[i] != value) {
                    break;
                }
                if(i == nums.length - 1) {
                    isEqual = true;
                }
            }

            if(!isEqual) {
                move(nums);
                result ++;
            }
        }

        return result;

    }

    private void move(int[] arr) {
        int maxIndex = 0;
        int max = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i]>max) {
                maxIndex = i;
                max = arr[i];
            }
        }

        for(int i=0; i<arr.length; i++) {
            if(i != maxIndex) {
                arr[i] = arr[i] + 1;
            }
        }
    }

    /**
     * Math:
     * 设 len = 数组长度, sum = 原数组的和, min = 原数组的最小值, neighborCount = 到达最终状态需要的步数 ,则有
     *  sum + neighborCount * (len - 1) = (min + neighborCount) * len
     *
     * 解得 neighborCount = sum - min * len
     *
     * 对结果的理解：
     *
     * Adding 1s to n - 1 elements effectively is equivalent to subtracting 1 from 1 element.
     * Therefore the question is transformed to: you are allowed to remove 1 from 1 element each steps,
     * and your goal is to make all element equal.
     *
     * After the removal, total sum is minNum * n, hence the number of step needed is sum - min * len.
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i : nums) {
            if(min > i) {
                min = i;
            }
            sum += i;
        }
        return sum - min * nums.length;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumMovesToEqualArrayElements().minMoves(new int[]{1,2147483647}));
    }

}
