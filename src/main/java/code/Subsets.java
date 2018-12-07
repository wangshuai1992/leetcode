package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-07 14:53
 */
public class Subsets {

    /**
     * say nums has 3 number, then we have 2^3 to place these nums
     * use binary 000 - 111 to represent these situations (when ith bit is 1, then put num[i] in the list)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;

        for(int i = 0; i < Math.pow(2, len); i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < len; j++) {
                int bit = len - 1 - j;
                if (((i >> bit) & 1) == 1) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * backtracking
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
//        result.add(new ArrayList<>(tempList));
//        for(int i = start; i < nums.length; i++) {
//            tempList.add(nums[i]);
//            //添加nums[i]的情况
//            backtrack(result, tempList, nums, i + 1);
//            //不添加nums[i]的情况
//            tempList.remove(Integer.valueOf(nums[i]));
//        }
        if(start == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            tempList.add(nums[start]);
            //添加nums[i]的情况
            backtrack(result, tempList, nums, start + 1);

            tempList.remove(Integer.valueOf(nums[start]));
            //不添加nums[i]的情况
            backtrack(result, tempList, nums, start + 1);
        }
    }

}
