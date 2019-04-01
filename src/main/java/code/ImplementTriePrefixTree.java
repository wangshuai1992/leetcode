package code;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-27 16:19
 */
public class ImplementTriePrefixTree {

    class Trie {

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setEnd();
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for(char c : prefix.toCharArray()) {
                if(!node.containsKey(c)) {
                    return null;
                }
                node = node.get(c);
            }
            return node;
        }
    }

    class TrieNode {

        private static final int R = 26;

        private TrieNode[] links = new TrieNode[R];

        private boolean isEnd;

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

}
