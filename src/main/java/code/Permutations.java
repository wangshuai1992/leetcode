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

    /**
     * O(n*n!) time
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        // track 中的元素会被标记为 true
        boolean[] used = new boolean[nums.length];
        // 结果
        List<List<Integer>> res = new LinkedList<>();
        // 记录回溯算法的递归路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, used, track, res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> track, List<List<Integer>> res) {
        // base case，到达叶子节点
        if (track.size() == nums.length) {
            // 收集叶子节点上的值
            res.add(new ArrayList<>(track));
            return;
        }

        // 回溯算法标准框架
        for (int i = 0; i < nums.length; i++) {
            // 已经存在 track 中的元素，不能重复选择
            if (used[i]) {
                continue;
            }
            // 做选择
            used[i] = true;
            track.add(nums[i]);
            // 进入下一层回溯树
            backtrack(nums, used, track, res);
            // 取消选择
            track.remove(track.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Permutations().permute(nums));
    }

}
