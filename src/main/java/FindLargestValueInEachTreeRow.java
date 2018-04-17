import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-17 17:23
 */
public class FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //每次while循环遍历一层节点
        while (!queue.isEmpty()) {
            //某一层所有节点的数目
            int tempSize = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i=0; i<tempSize; i++) {
                TreeNode node = queue.remove();
                if(node.val > max) {
                    max = node.val;
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }
        return result;
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
