package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-23 10:52
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        Set<Integer> resSet = new HashSet<>();
        for (int i : nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }

        int[] res = new int[resSet.size()];
        int index = 0;
        for (int i : resSet) {
            res[index] = i;
            index++;
        }
        return res;
    }

    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }

        set1.retainAll(set2);

        int[] res = new int[set1.size()];
        int index = 0;
        for (int i : set1) {
            res[index] = i;
            index++;
        }
        return res;
    }

}
