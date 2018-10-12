package code;

import java.util.*;

/**
 * https://leetcode.com/problems/all-possible-full-binary-trees/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-09 15:54
 */
public class AllPossibleFullBinaryTrees {

    //缓存方法结果 减少重复运算
    private Map<Integer, List<TreeNode>> cache = new HashMap<>();

    /**
     * recursion
     *
     * @param n
     * @return
     */
    public List<TreeNode> allPossibleFBT(int n) {

        if(cache.containsKey(n)) {
            return cache.get(n);
        }

        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            res.add(null);
            return res;
        }
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if((n & 1) == 0) {
            //为奇数才可能有full binary tree
            return res;
        }

        for (int i = 0; i < n; i++) {
            //i为根节点左子树的节点数量
            List<TreeNode> lefts = allPossibleFBT(i);
            if (lefts.isEmpty()) {
                //排除不能形成完全二叉树的情况
                continue;
            }
            List<TreeNode> rights = allPossibleFBT(n - i - 1);
            if (rights.isEmpty()) {
                //排除不能形成完全二叉树的情况
                continue;
            }

            for (TreeNode node1 : lefts) {
                for (TreeNode node2 : rights) {
                    TreeNode root = new TreeNode(0);
                    if((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
                        //子节点不同时为空或者不为空
                        continue;
                    }
                    root.left = node1;
                    root.right = node2;
                    res.add(root);
                }
            }
        }
        cache.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        for (TreeNode node : new AllPossibleFullBinaryTrees().allPossibleFBT(7)) {
            System.out.println("===============");
            node.print();
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        /**
         * 将树转换成字符串并打印在控制台上，用L表示左孩子，R表示右孩子
         */
        public void print() {
            List<TreeNode> tree = locateNodes();
            int size = tree.size();
            int deepth = 0;
            // 获取树的深度
            while ((size >>= 1) > 0) {
                deepth++;
            }
            deepth++;
            Iterator<TreeNode> iterator = tree.iterator();
            int maxPosition = 1;
            // 层数，从1开始
            for (int floor = 1; floor <= deepth; floor++) {
                maxPosition = 1 << (floor - 1);
                printBlank((1 << (deepth - floor)) - 1);
                for (int position = 0; position < maxPosition; position++) {
                    if (iterator.hasNext()) {
                        TreeNode node = iterator.next();
                        if (node != null) {
                            System.out.print(node);
                        } else {
                            System.out.print(" ");
                        }
                        printBlank((1 << (deepth - floor + 1)) - 1);
                    }
                }
                System.out.println();
            }
        }

        /**
         * 将二叉树用空节点补充成完全二叉树，并以水平遍历形式返回
         *
         * @return
         */
        private List<TreeNode> locateNodes() {
            Queue<TreeNode> queue = new LinkedList<>();

            // 把树补充成完全二叉树，放在这个链表中
            List<TreeNode> tree = new LinkedList<>();
            queue.add(this);

            // 队列中非空节点数
            int nodeCount = 1;
            while (!queue.isEmpty() && nodeCount > 0) {
                TreeNode node = queue.remove();
                if (node != null) {
                    nodeCount--;
                    tree.add(node);
                    TreeNode leftNode = node.left;
                    TreeNode rightNode = node.right;
                    if (leftNode == null) {
                        queue.add(null);
                    } else {
                        queue.add(leftNode);
                        nodeCount++;
                    }
                    if (rightNode == null) {
                        queue.add(null);
                    } else {
                        queue.add(rightNode);
                        nodeCount++;
                    }
                } else {
                    tree.add(null);
                    queue.add(null);
                    queue.add(null);
                }
            }
            return tree;
        }

        private void printBlank(int length) {
            while (length-- > 0) {
                System.out.print(" ");
            }
        }

        @Override
        public String toString() {
            return "" + val;
        }

    }

}
