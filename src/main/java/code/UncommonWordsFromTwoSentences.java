package code;

import java.util.*;

/**
 * https://leetcode.com/problems/uncommon-words-from-two-sentences/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-12 16:53
 */
public class UncommonWordsFromTwoSentences {

    public String[] uncommonFromSentences(String a, String b) {
        Map<String, Integer> count = new HashMap<>();
        List<String> aList = Arrays.asList(a.split(" "));
        List<String> bList = Arrays.asList(b.split(" "));
        for(String str : aList) {
            count.merge(str, 1, (oldVal, newVal) -> oldVal + newVal);
        }
        for(String str : bList) {
            count.merge(str, 1, (oldVal, newVal) -> oldVal + newVal);
        }
        return count.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }

    public String[] uncommonFromSentences1(String a, String b) {
        Map<String, Integer> count = new HashMap<>();
        for (String word: a.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        for (String word: b.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String> ans = new LinkedList<>();
        for (String word: count.keySet()) {
            if (count.get(word) == 1) {
                ans.add(word);
            }
        }

        return ans.toArray(new String[ans.size()]);
    }

}
