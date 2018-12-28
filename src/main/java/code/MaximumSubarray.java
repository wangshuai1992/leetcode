package code;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-24 16:25
 */
public class MaximumSubarray {

    /**
     * Brute Force
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums[i];
                }
                result = Math.max(result, sum);
            }
        }
        return result;
    }

    /**
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int sum = 0;
        int max = nums[0];
        for(int x : nums) {
            sum += x;
            max = Math.max(max, sum);
            if(sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    /**
     * algorithm that operates on arrays: it starts at the left end (element A[1]) and scans through to the right
     * end (element A[n]), keeping track of the maximum sum subvector seen so far. The maximum is initially A[0].
     *
     * Suppose we've solved the problem for A[1 .. i - 1]; how can we extend that to A[1 .. i]?
     * The maximum sum in the first I elements is either the maximum sum in the first i - 1 elements
     * (which we'll call MaxSoFar), or it is that of a subvector that ends in position i (which we'll call MaxEndingHere).
     *
     * MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        //global max
        int maxSoFar = nums[0];
        //local max (must include nums[i])
        int maxEndingHere = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int x = nums[i];
            maxEndingHere = Math.max(maxEndingHere + x, x);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /**
     * Divide and Conquer / O(nlgn) time
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }

    private int find(int[] nums, int start, int end) {
        if(start == end) {
            return nums[start];
        }
        if(start > end) {
            return Integer.MIN_VALUE;
        }

        int mid = (start + end) / 2;

        //left
        int leftMax = find(nums, start, mid - 1);
        //right
        int rightMax = find(nums, mid + 1, end);

        // mid to left
        int sum = 0;
        int ml = 0;
        for(int i = mid - 1; i >= start; --i) {
            sum += nums[i];
            ml = Math.max(ml, sum);
        }

        //mid to right
        sum = 0;
        int mr = 0;
        for(int i = mid + 1; i <= end; ++i) {
            sum += nums[i];
            mr = Math.max(mr, sum);
        }
        return Math.max(Math.max(leftMax, rightMax), ml + nums[mid] + mr);
    }

}
