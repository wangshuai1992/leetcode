package code;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/house-robber-iii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-10 14:37
 */
public class HouseRobberIII {

    /**
     * Brute Force Recursive
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int rootRobbed = root.val;
        //calculate grandchild subtrees
        if(root.left != null) {
            rootRobbed += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null) {
            rootRobbed += rob(root.right.left) + rob(root.right.right);
        }

        //rob root vs not rob root
        return Math.max(rootRobbed, rob(root.left) + rob(root.right));
    }

    /**
     * in brute force solution, in order to calculate rob(root.left), we also need to cal rob(root.left.left), which we have
     * done that in rob(root), and this leads to overlapping of the subproblems.
     *
     * use a hash map to record the results for visited subtrees
     *
     * @param root
     * @return
     */
    public int rob1(TreeNode root) {
        Map<TreeNode, Integer> cache = new HashMap<>();
        return robWithCache(root, cache);
    }

    private int robWithCache(TreeNode cur, Map<TreeNode, Integer> cache) {
        if(cur == null) {
            return 0;
        }
        if(cache.containsKey(cur)) {
            return cache.get(cur);
        }

        int robCur = cur.val;
        //calculate grandchild subtrees
        if(cur.left != null) {
            robCur += robWithCache(cur.left.left, cache) + robWithCache(cur.left.right, cache);
        }
        if(cur.right != null) {
            robCur += robWithCache(cur.right.left, cache) + robWithCache(cur.right.right, cache);
        }

        //rob root vs not rob root
        int max = Math.max(robCur, robWithCache(cur.left, cache) + robWithCache(cur.right, cache));
        cache.put(cur, max);
        return max;
    }

    /**
     * let's take one step back and ask why we have overlapping subproblems.
     * If you trace all the way back to the beginning, you'll find the answer lies in the way
     * how we have defined rob(root). As I mentioned, for each tree root, there are two scenarios:
     * it is robbed or is not. rob(root) does not distinguish between these two cases, so "information is lost as
     * the recursion goes deeper and deeper", which results in repeated subproblems.
     *
     * by keeping track of the information of both scenarios, we decoupled the subproblems and the solution
     * essentially boiled down to a greedy one.
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    /**
     *
     * @param root
     * @return int[0] - when not rob root
     *         int[1] - when rob root
     */
    private int[] robSub(TreeNode root) {
        int[] res = new int[2];
        if(root == null) {
            return res;
        }

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

}
