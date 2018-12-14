package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-14 09:31
 */
public class CombinationSum {

    /**
     * Recursive
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return recurse(candidates, 0, target);
    }

    private List<List<Integer>> recurse(int[] candidates, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        if (start == candidates.length) {
            return res;
        }

        int x = candidates[start];
        //use how many x
        int times = 0;
        while (x * times <= target) {
            List<List<Integer>> sub = recurse(candidates, start + 1, target - x * times);
            for (List<Integer> eList : sub) {
                List<Integer> newList = new ArrayList<>(eList);
                for (int i = 0; i < times; i++) {
                    newList.add(x);
                }
                res.add(newList);
            }
            times++;
        }
        return res;
    }

    /**
     * backtracking
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            res.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backtrack(res, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{7}, 7));
    }

}
