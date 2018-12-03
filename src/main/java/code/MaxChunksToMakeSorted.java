package code;

/**
 * https://leetcode.com/problems/max-chunks-to-make-sorted/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-29 11:23
 */
public class MaxChunksToMakeSorted {

    /**
     * One Pass / O(N) time
     *
     * i and arr[i] must be in the same chunk
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int result = 0;

        int target = -1;
        for(int i = 0; i < len; i++) {
            // set/update target
            target = Math.max(target, arr[i]);

            if (i == target) {
                result++;
            }
        }

        return result;
    }

}
