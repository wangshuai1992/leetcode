package code;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-26 17:27
 */
public class PathSumIII {

    /**
     * calculate the result that begins with each node of the tree, then add them together
     *
     * O(n^2) time
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int result = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result += pathSumFrom(node, sum);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * Recursive
     *
     * Space: O(n) due to recursion.
     * Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
     *
     * pathSumFrom takes O(n)
     * pathSum has recurrence relation T(n) = n + 2T(n/2) = nlogn for balance tree.
     * pathSum has recurrence relation T(n) = n + T(n-1) = n^2 for linear tree.
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum1(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, sum) + pathSum1(root.left, sum) + pathSum1(root.right, sum);
    }

    /**
     * returns the path number which begins with the given node, O(n) time
     */
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int res = node.val == sum ? 1 : 0;
        res += pathSumFrom(node.left, sum - node.val);
        res += pathSumFrom(node.right, sum - node.val);
        return res;
    }

    /**
     * When I come to one node, I want to find all paths ended with current node, whose sum equals to target.
     *
     * using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) ,
     * and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does,
     * we added up the ways of prefix sum - target into res.
     *
     * O(n) time
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum2(TreeNode root, int sum) {
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return recurse(root, 0, sum, preSumMap);
    }

    private int recurse(TreeNode root, int currSum, int target, Map<Integer, Integer> preSumMap) {
        if(root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSumMap.getOrDefault(currSum - target, 0);

        preSumMap.put(currSum, preSumMap.getOrDefault(currSum, 0) + 1);
        res += recurse(root.left, currSum, target, preSumMap) + recurse(root.right, currSum, target, preSumMap);
        //back to upper level, clean the map
        preSumMap.put(currSum, preSumMap.getOrDefault(currSum, 0) - 1);
        return res;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
