package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-17 10:18
 */
public class AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        traverse(root, list, 0);

        List<Double> result = new ArrayList<>();

        for (List<Integer> subList : list) {
            long sum = 0;
            int num = 0;
            for (int n : subList) {
                sum += n;
                num++;
            }
            result.add((double) sum / num);
        }
        return result;
    }

    private void traverse(TreeNode root, List<List<Integer>> list, int i) {
        if (list.size() <= i || list.get(i) == null) {
            List<Integer> e = new ArrayList<>();
            e.add(root.val);
            list.add(i, e);
        } else {
            list.get(i).add(root.val);
        }

        if (root.left != null) {
            traverse(root.left, list, i + 1);
        }
        if (root.right != null) {
            traverse(root.right, list, i + 1);
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);
        System.out.println(new AverageOfLevelsInBinaryTree().averageOfLevels(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
