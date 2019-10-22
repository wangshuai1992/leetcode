package code;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-09-30 14:49
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

    public TreeNode bstFromPreorder(int[] preorder) {
        return recurse(preorder, 0, preorder.length);
    }

    private TreeNode recurse(int[] arr, int start, int end) {
        if(start >= end || start >= arr.length) {
            return null;
        }

        int rootVal = arr[start];
        TreeNode root = new TreeNode(rootVal);

        for(int i = start + 1; i < end; i++) {
            if(arr[i] > rootVal) {
                root.left = recurse(arr, start + 1, i);
                root.right = recurse(arr, i, end);
                return root;
            }
        }
        root.left = recurse(arr, start + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,5,1,7,10,12};
        new ConstructBinarySearchTreeFromPreorderTraversal().bstFromPreorder(arr);
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
