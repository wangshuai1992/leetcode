package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/beautiful-arrangement-ii/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-20 17:53
 */
public class BeautifulArrangementII {

    /**
     * Brute Force
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray1(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> permutations = new ArrayList<>();

        permute(permutations, nums, 0);
        for (List<Integer> cand : permutations) {
            if (numUniqueDiffs(cand) == k) {
                int[] ans = new int[n];
                int i = 0;
                for (int x : cand) {
                    ans[i++] = x;
                }
                return ans;
            }
        }
        return null;
    }

    private void permute(List<List<Integer>> ans, int[] nums, int start) {
        if (start >= nums.length) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int x : nums) {
                cur.add(x);
            }
            ans.add(cur);
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                permute(ans, nums, start + 1);
                swap(nums, start, i);
            }
        }
    }

    private int numUniqueDiffs(List<Integer> arr) {
        boolean[] seen = new boolean[arr.size()];
        int ans = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            int delta = Math.abs(arr.get(i) - arr.get(i + 1));
            if (!seen[delta]) {
                ans++;
                seen[delta] = true;
            }
        }
        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Construction
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int c = 0;
        for (int v = 1; v < n-k; v++) {
            ans[c++] = v;
        }
        for (int i = 0; i <= k; i++) {
            ans[c++] = (i%2 == 0) ? (n-k + i/2) : (n - i/2);
        }
        return ans;
    }

}
