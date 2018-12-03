package code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-30 17:18
 */
public class ContainsDuplicate {

    /**
     * HashSet
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            if(set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }

    /**
     * Sorting
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }

}
