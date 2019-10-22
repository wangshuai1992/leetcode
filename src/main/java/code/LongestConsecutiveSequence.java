package code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-02-28 17:20
 */
public class LongestConsecutiveSequence {

    /**
     * TreeSet / O(nlogn) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new TreeSet<>();
        for (int n : nums) {
            set.add(n);
        }

        Integer prev = null;
        int count = 1;
        int max = 1;
        for (int n : set) {
            if (prev != null) {
                if (n - prev == 1) {
                    count++;
                } else {
                    max = Math.max(count, max);
                    count = 1;
                }
            }
            prev = n;
        }
        return Math.max(count, max);
    }

    /**
     * Sorting / O(nlogn) time, O(1) space
     *
     * @param nums
     * @return
     */
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int max = 1;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    count += 1;
                } else {
                    max = Math.max(max, count);
                    count = 1;
                }
            }
        }

        return Math.max(max, count);
    }

    /**
     * HashSet and Intelligent Sequence Building / O(n) time, O(n) space
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }

        int max = 1;
        int count = 1;
        for(int n : set) {
            //只从连续数列的头部开始 确保不会重复
            if(!set.contains(n - 1)) {
                int cur = n;

                while (set.contains(cur + 1)) {
                    count ++;
                    cur ++;
                }

                max = Math.max(max, count);
                count = 1;
            }
        }
        return max;
    }

}
