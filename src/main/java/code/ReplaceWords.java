package code;

import java.util.*;

/**
 * https://leetcode.com/problems/replace-words/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-30 11:00
 */
public class ReplaceWords {

    /**
     * prefix hash
     *
     * @param roots
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> roots, String sentence) {
        Set<String> rootset = new HashSet<>();
        rootset.addAll(roots);

        StringBuilder ans = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootset.contains(prefix)) {
                    break;
                }
            }
            if (ans.length() > 0) {
                ans.append(" ");
            }
            ans.append(prefix);
        }
        return ans.toString();
    }

    /**
     * Trie
     *
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords1(List<String> dict, String sentence) {
        TrieTree tree = new TrieTree();
        for (String s : dict) {
            tree.add(s);
        }
        StringBuilder res = new StringBuilder();
        for (String sub : sentence.split(" ")) {
            String prefix = tree.getShortestPrefix(sub);
            if (null == prefix) {
                res.append(sub);
            } else {
                res.append(prefix);
            }
            res.append(' ');
        }
        return res.deleteCharAt(res.length() - 1).toString();
    }

    private static class TrieTree {

        TrieNode root;

        public TrieTree() {
            root = new TrieNode(false, ' ');
        }

        public void add(String str) {
            TrieNode parent = root;
            for (int i = 0; i < str.length(); i++) {
                List<TrieNode> children = parent.children;
                char c = str.charAt(i);

                boolean isFound = false;
                for (TrieNode node : children) {
                    if (node.ch == c && i == str.length() - 1) {
                        node.isWord = true;
                        return;
                    }
                    if (node.ch == c) {
                        parent = node;
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    TrieNode newNode = new TrieNode(i == str.length() - 1, c);
                    parent.children.add(newNode);
                    parent = newNode;
                }
            }
        }

        public String getShortestPrefix(String str) {
            StringBuilder result = new StringBuilder();
            TrieNode parent = root;
            int index = 0;
            while (!parent.children.isEmpty() && index < str.length()) {
                boolean isFound = false;
                for (TrieNode node : parent.children) {
                    if (node.ch == str.charAt(index) && node.isWord) {
                        result.append(node.ch);
                        return result.toString();
                    }
                    if (node.ch == str.charAt(index)) {
                        result.append(node.ch);
                        parent = node;
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    return null;
                } else {
                    index++;
                }
            }
            return null;
        }

    }

    private static class TrieNode {

        boolean isWord;

        char ch;

        List<TrieNode> children = new ArrayList<>();

        public TrieNode(boolean isWord, char ch) {
            this.isWord = isWord;
            this.ch = ch;
        }
    }

    /**
     * Trie
     *
     * @param roots
     * @param sentence
     * @return
     */
    public String replaceWords2(List<String> roots, String sentence) {
        TrieNode1 trie = new TrieNode1();
        for (String root : roots) {
            TrieNode1 cur = trie;
            for (char letter : root.toCharArray()) {
                if (cur.children[letter - 'a'] == null) {
                    cur.children[letter - 'a'] = new TrieNode1();
                }
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }

        StringBuilder ans = new StringBuilder();

        for (String word : sentence.split("\\s+")) {
            if (ans.length() > 0) {
                ans.append(" ");
            }

            TrieNode1 cur = trie;
            for (char letter : word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null) {
                    break;
                }
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }
        return ans.toString();
    }

    class TrieNode1 {
        TrieNode1[] children;
        String word;

        TrieNode1() {
            children = new TrieNode1[26];
        }
    }

}
