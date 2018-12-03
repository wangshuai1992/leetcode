package code;

/**
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-30 17:30
 */
public class MaximumXOROfTwoNumbersInAnArray {

    /**
     * Brute Force / O(n ^ 2) time
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    /**
     * Trie
     *
     * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/130427
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR1(int[] nums) {
        Trie root = buildTrie(nums);
        int max = 0;
        int start = 0;

        //从根节点开始，如果左右节点只有一个不为空，说明nums中所有数字在这一位的值都相同，那么所有的异或结果在这一位都为0，可以跳过
        //直到左右节点都不为空
        while (root != null) {
            if (root.left != null && root.right != null) {
                break;
            }
            root = root.left != null ? root.left : root.right;
            start++;
        }

        for(int num : nums) {
            //对于每一个数字 都从优化后的根节点开始
            Trie cur = root;
            int xor = 0;
            for(int i = 31 - start; i >= 0; i--) {
                int val = (num >> i) & 1;
                //左右节点必有一个不为空
                if(val == 0) {
                    cur = cur.right != null ? cur.right : cur.left;
                } else {
                    cur = cur.left != null ? cur.left : cur.right;
                }
                //按位作异或运算
                xor += (val ^ cur.val) << i;
            }
            max = Math.max(max, xor);
        }
        return max;
    }

    private Trie buildTrie(int[] nums) {
        Trie root = new Trie(0);
        Trie cur = root;
        for (int num : nums) {
            for (int i = 31; i >= 0; i--) {
                int val = (num >> i) & 1;
                if (val == 0) {
                    if (cur.left == null) {
                        cur.left = new Trie(0);
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = new Trie(1);
                    }
                    cur = cur.right;
                }
            }
            cur = root;
        }
        return root;
    }

    private static class Trie {
        int val;
        Trie left;
        Trie right;

        public Trie(int val) {
            this.val = val;
        }
    }

}
