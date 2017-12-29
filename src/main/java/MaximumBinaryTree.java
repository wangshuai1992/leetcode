import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/maximum-binary-tree/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-18 16:41
 */
public class MaximumBinaryTree {

    /**
     *
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int rootIndex = getMaxIndex(nums);

        TreeNode treeNode = new TreeNode(nums[rootIndex]);

        int[] left = Arrays.copyOfRange(nums, 0, rootIndex);
        int[] right = Arrays.copyOfRange(nums, rootIndex + 1, nums.length);

        if(left.length == 1) {
            treeNode.left = new TreeNode(left[0]);
        } else if (left.length != 0){
            treeNode.left = constructMaximumBinaryTree(left);
        }

        if(right.length == 1) {
            treeNode.right = new TreeNode(right[0]);
        } else if(right.length != 0) {
            treeNode.right = constructMaximumBinaryTree(right);
        }

        return treeNode;
    }

    private int getMaxIndex(int[] nums) {
        int temp = 0;
        int maxIndex = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            int val = nums[i];
            if (val > temp) {
                temp = val;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

     */

    /**
     * Each node went into stack once, and went out stack once. Worst case time complexity O(N).
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (int num : nums) {
            TreeNode curr = new TreeNode(num);
            while (!stack.isEmpty() && stack.peek().val < num) {
                curr.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }

        return stack.isEmpty() ? null : stack.removeLast();
    }

    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 6, 0, 5};

        MaximumBinaryTree solution = new MaximumBinaryTree();

        System.out.println(solution.constructMaximumBinaryTree(arr).val);
    }

}
