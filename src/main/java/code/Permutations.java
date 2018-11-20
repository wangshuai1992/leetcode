package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-16 18:02
 */
public class Permutations {

    /**
     * 选取任意一个元素 将它插入剩余元素所有组合的元素间空位中（包括头尾）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        if (nums.length == 1) {
            List<Integer> temp = new LinkedList<>();
            temp.add(nums[0]);
            result.add(temp);
            return result;
        }

        int last = nums[nums.length - 1];
        for (List<Integer> list : permute(Arrays.copyOf(nums, nums.length - 1))) {
            List<Integer> temp;
            for (int i = 0; i < nums.length; i++) {
                temp = new LinkedList<>(list);
                temp.add(i, last);
                result.add(temp);
            }
        }
        return result;
    }

    /**
     * backtracking
     *
     * 创建一个空的list，依次选取元素添加入list（判断是否存在），并根据不同的添加顺序回溯
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    // element already exists, skip
                    continue;
                }
                tempList.add(num);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Permutations().permute(nums));
    }

}
