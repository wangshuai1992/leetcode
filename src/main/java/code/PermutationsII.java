package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-16 21:21
 */
public class PermutationsII {

    /**
     * backtrack / O(n * n!) time
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, used, track, res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 剪枝逻辑，固定相同的元素在排列中的相对位置
            // 当出现重复元素时，比如输入 nums = [1,2,2',2'']，2' 只有在 2 已经被使用的情况下才会被选择，
            // 同理，2'' 只有在 2' 已经被使用的情况下才会被选择，这就保证了相同元素在排列中的相对位置保证固定。
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            track.add(nums[i]);
            backtrack(nums, used, track, res);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

}
