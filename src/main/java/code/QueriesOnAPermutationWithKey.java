package code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode.com/problems/queries-on-a-permutation-with-key/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-04-29 15:43
 */
public class QueriesOnAPermutationWithKey {

    /**
     * linked list  O(n^2) time
     *
     * @param queries
     * @param m
     * @return
     */
    public int[] processQueries(int[] queries, int m) {
        int[] result = new int[queries.length];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        for (int i = 0; i < queries.length; i++) {
            int num = queries[i];
            int index = list.indexOf(num);
            result[i] = index;
            if (i != queries.length - 1) {
                list.addFirst(list.remove(index));
            }
        }
        return result;
    }

    /**
     * Binary indexed tree  O(nlogn)
     *
     * https://leetcode.com/problems/queries-on-a-permutation-with-key/discuss/575208/Java-Binary-indexed-tree-solution
     * https://blog.csdn.net/Yaokai_AssultMaster/article/details/79492190
     *
     * @param queries
     * @param m
     * @return
     */
    public int[] processQueries1(int[] queries, int m) {
        int n = queries.length;
        BIT bit = new BIT(n + m + 1);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            bit.add(n + i, 1);
            map.put(i, n + i);
        }


        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int index = map.remove(queries[i]);
            res[i] = bit.prefixSum(index - 1);

            int newIndex = n - i;
            bit.add(index, -1);
            bit.add(newIndex, 1);
            map.put(queries[i], newIndex);
        }
        return res;
    }

    private static class BIT {
        int[] a;

        BIT(int n) {
            a = new int[n];
        }

        public void add(int index, int delta) {
            index++;
            while (index < a.length) {
                a[index] += delta;
                index += index & (-index);
            }
        }

        int prefixSum(int index) {
            index++;
            int res = 0;
            while (index > 0) {
                res += a[index];
                index -= index & (-index);
            }
            return res;
        }
    }

}
