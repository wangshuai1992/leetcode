package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-10-22 15:52
 */
public class NRepeatedElementInSize2NArray {

    public int repeatedNTimes(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return 0;
    }

    /**
     * If we ever find a repeated element, it must be the answer. Let's call this answer the major element.
     *
     * Consider all subarrays of length 4. There must be a major element in at least one such subarray.
     * This is because either:
     * 1. There is a major element in a length 2 subarray, or;
     * 2. Every length 2 subarray has exactly 1 major element, which means that a length 4 subarray that begins at a major element will have 2 major elements.
     * Thus, we only have to compare elements with their neighbors that are distance 1, 2, or 3 away.
     *
     * @param arr
     * @return
     */
    public int repeatedNTimes1(int[] arr) {
        for (int k = 1; k <= 3; k++) {
            for (int i = 0; i < arr.length - k; i++) {
                if (arr[i] == arr[i + k]) {
                    return arr[i];
                }
            }
        }
        return 0;
    }

}
