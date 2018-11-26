package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/map-sum-pairs/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-23 11:21
 */
public class MapSumPairs {

    /**
     * brute force
     */
    private class MapSum {

        private final Map<String, Integer> map;

        public MapSum() {
            map = new HashMap<>();
        }

        /**
         * O(1)
         *
         * @param key
         * @param val
         */
        public void insert(String key, int val) {
            map.put(key, val);
        }

        /**
         * O(N * P) where N is the number of items in the map, and P is the length of the input prefix.
         *
         * @param prefix
         * @return
         */
        public int sum(String prefix) {
            int sum = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().startsWith(prefix)) {
                    sum += entry.getValue();
                }
            }
            return sum;
        }
    }

    /**
     * Prefix Hashmap (sapce for time)
     * <p>
     * We can remember the answer for all possible prefixes in a HashMap score. When we get a new (key, val) pair,
     * we update every prefix of key appropriately: each prefix will be changed by delta = val - map[key],
     * where map is the previous associated value of key (zero if undefined.)
     */
    private class MapSum1 {

        private Map<String, Integer> map;

        private Map<String, Integer> sumMap;

        public MapSum1() {
            map = new HashMap<>();
            sumMap = new HashMap<>();
        }

        /**
         * O(K)
         *
         * @param key
         * @param val
         */
        public void insert(String key, int val) {
            //考虑到可能替换原有的key
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);

            StringBuilder prefix = new StringBuilder();
            for (char c : key.toCharArray()) {
                prefix.append(c);
                sumMap.put(prefix.toString(), sumMap.getOrDefault(prefix.toString(), 0) + delta);
            }
        }

        /**
         * O(1)
         *
         * @param prefix
         * @return
         */
        public int sum(String prefix) {
            return sumMap.getOrDefault(prefix, 0);
        }
    }

    /**
     * Trie Tree
     */
    private class MapSum2 {
        HashMap<String, Integer> map;
        TrieNode root;

        public MapSum2() {
            map = new HashMap<>();
            root = new TrieNode();
        }

        /**
         * O(K), where K is the length of the key.
         *
         * @param key
         * @param val
         */
        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);

            TrieNode cur = root;
            cur.score += delta;
            for (char c : key.toCharArray()) {
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
                cur.score += delta;
            }
        }

        /**
         * O(K)
         *
         * @param prefix
         * @return
         */
        public int sum(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                cur = cur.children.get(c);
                if (cur == null) {
                    return 0;
                }
            }
            return cur.score;
        }
    }

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int score;
    }

}
