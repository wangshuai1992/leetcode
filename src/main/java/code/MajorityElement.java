package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/majority-element/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-26 17:54
 */
public class MajorityElement {

    /**
     * use HashMap / O(n) time
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            int count = map.getOrDefault(i, 0) + 1;
            if (count > nums.length / 2) {
                return i;
            }
            map.put(i, count);
        }
        return 0;
    }

    /**
     * Sorting / O(nlogn) time
     * <p>
     * after sort , the majority element can be found at index n / 2 (and n / 2 + 1, if n is even)
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * Divide and Conquer
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, neighborCount each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    /**
     * 多数投票算法 Boyer-Moore Voting Algorithm
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        int count = 0;
        int candidate = nums[0];
        for(int i : nums) {
            //when neighborCount = 0, change candidate
            if(count == 0) {
                candidate = i;
            }
            count += candidate == i ? 1 : -1;
        }
        return candidate;
    }

}
