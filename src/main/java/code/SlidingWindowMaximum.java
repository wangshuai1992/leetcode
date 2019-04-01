package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-22 17:25
 */
public class SlidingWindowMaximum {

    /**
     * O(NK) time
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < result.length; i++) {
            int end = i + k - 1;
            int max = Integer.MIN_VALUE;
            for (int j = i; j <= end; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * using deque
     * https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // q contains index... r contains content
            queue.offer(i);
            if (i >= k - 1) {
                r[ri++] = nums[queue.peek()];
            }
        }
        return r;
    }

    /**
     * two pass
     * https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array
     *
     * For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4
     *
     * 1. partition the array in blocks of size w=4. The last block may have less then w.
     * 2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|
     *
     * 2. Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
     * left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56
     *
     * 3. Similarly calculate max in future by traversing from end to start.
     * right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56
     *
     * 4. now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
     * sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int[] maxLeft = new int[nums.length];
        int[] maxRight = new int[nums.length];

        maxLeft[0] = nums[0];
        maxRight[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            maxLeft[i] = (i % k == 0) ? nums[i] : Math.max(maxLeft[i - 1], nums[i]);

            int j = nums.length - i - 1;
            maxRight[j] = (j % k == 0) ? nums[j] : Math.max(maxRight[j + 1], nums[j]);
        }

        int[] slidingMax = new int[nums.length - k + 1];
        for (int i = 0; i + k <= nums.length; i++) {
            slidingMax[i] = Math.max(maxRight[i], maxLeft[i + k - 1]);
        }

        return slidingMax;
    }

}
