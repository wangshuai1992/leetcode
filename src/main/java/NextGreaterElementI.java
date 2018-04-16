import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/next-greater-element-i/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-13 11:03
 */
public class NextGreaterElementI {

    /*
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            for (int j = search(nums2, num) + 1; j < nums2.length; j++) {
                int num2 = nums2[j];
                if(num2 > num) {
                    result[i] = num2;
                    break;
                }
            }
            if(result[i] == 0) {
                result[i] = -1;
            }
        }
        return result;
    }

    private int search(int[] arr, int num) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == num) {
                return i;
            }
        }
        return -1;
    }
    */

    /**
     * Key observation:
     * Suppose we have a decreasing sequence followed by a greater number
     * For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers
     * in the sequence
     *
     * We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop
     * all elements less than x and for all the popped ones, their next greater element is x
     *
     * For example [9, 8, 7, 3, 2, 1, 6]
     * The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3
     * whose next greater element should be 6
     *
     * @param findNums
     * @param nums
     * @return
     */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < findNums.length; i++) {
            findNums[i] = map.getOrDefault(findNums[i], -1);
        }
        return findNums;
    }

}
