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
        return buildTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int from, int to) {
        if (from > to) {
            return null;
        }
        int rootVal = preorder[from];
        TreeNode root = new TreeNode(rootVal);
        if (from == to) {
            return root;
        }
        // 注意 没有右子树时这个index是右边界
        int rightStartIndex = to + 1;
        for (int i = from + 1; i <= to; i++) {
            if (preorder[i] > rootVal) {
                rightStartIndex = i;
                break;
            }
        }
        root.left = buildTree(preorder, from + 1, rightStartIndex - 1);
        root.right = buildTree(preorder, rightStartIndex, to);
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
