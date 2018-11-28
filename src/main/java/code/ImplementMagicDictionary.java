package code;

import java.util.*;

/**
 * https://leetcode.com/problems/implement-magic-dictionary/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-28 11:04
 */
public class ImplementMagicDictionary {

    private class MagicDictionary {

        Map<Integer, Set<String>> map;

        public MagicDictionary() {
            map = new HashMap<>();
        }

        /**
         * O(N) time
         *
         * @param dict
         */
        public void buildDict(String[] dict) {
            for (String s : dict) {
                int len = s.length();
                Set<String> set = map.get(len);
                if (set == null) {
                    set = new HashSet<>();
                    set.add(s);
                    map.put(len, set);
                } else {
                    set.add(s);
                }
            }
        }

        /**
         * O(NK) time , N = the number of wordSet in our magic dictionary, K = the length of the search word
         * @param word
         * @return
         */
        public boolean search(String word) {
            int len = word.length();
            Set<String> set = map.get(len);
            if (set == null) {
                return false;
            } else {
                for (String s : set) {
                    if (s.equals(word)) {
                        continue;
                    }
                    int dif = 0;
                    for (int i = 0; i < len; i++) {
                        if (word.charAt(i) != s.charAt(i)) {
                            dif++;
                        }
                    }
                    if (dif == 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * Generalized Neighbors / use '*' to represent the changed char
     */
    public class MagicDictionary1 {
        Set<String> wordSet;
        Map<String, Integer> neighborCount;

        public MagicDictionary1() {
            wordSet = new HashSet<>();
            neighborCount = new HashMap<>();
        }

        private List<String> generalizedNeighbors(String word) {
            List<String> ans = new ArrayList<>();
            char[] chars = word.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                char c = chars[i];
                chars[i] = '*';
                ans.add(new String(chars));
                chars[i] = c;
            }
            return ans;
        }

        /**
         * O(sum(Wi^2)) time ,Wi is the length of word[i]
         *
         * @param words
         */
        public void buildDict(String[] words) {
            for(String word : words) {
                wordSet.add(word);
                for(String str : generalizedNeighbors(word)) {
                    neighborCount.put(str, neighborCount.getOrDefault(str, 0) + 1);
                }
            }
        }

        /**
         * O(K^2) to search , K = the length of the search word
         *
         * @param word
         * @return
         */
        public boolean search(String word) {
            for(String str : generalizedNeighbors(word)) {
                int count = neighborCount.getOrDefault(str, 0);
                if(count > 1 || (count == 1 && !wordSet.contains(word))) {
                    return true;
                }
            }
            return false;
        }
    }

}
