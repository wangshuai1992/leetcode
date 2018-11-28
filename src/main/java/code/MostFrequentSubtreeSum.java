package code;

import java.util.*;

/**
 * https://leetcode.com/problems/most-frequent-subtree-sum/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-30 09:44
 */
public class MostFrequentSubtreeSum {

    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) {
            return new int[0];
        }

        //key:sum, value:frequence
        Map<Integer, Integer> map = new HashMap<>();
        traverseTree(root, map);
        int maxFreq = Collections.max(map.values());

        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == maxFreq) {
                list.add(entry.getKey());
            }
        }

        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;

    }

    /**
     * this method return sum of the tree and record it in map
     *
     * @param root
     * @param map
     * @return
     */
    private int traverseTree(TreeNode root, Map<Integer,Integer> map) {
        if(root == null) {
            return 0;
        }

        int sum = traverseTree(root.left, map) + traverseTree(root.right, map) + root.val;
        if(map.containsKey(sum)) {
            map.put(sum, map.get(sum) + 1);
        } else {
            map.put(sum, 1);
        }
        return sum;
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
