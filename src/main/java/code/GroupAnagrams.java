package code;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-19 10:45
 */
public class GroupAnagrams {

    /**
     * Categorize by Sorted String
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cArr = str.toCharArray();
            Arrays.sort(cArr);
            String id = String.valueOf(cArr);
            if (!map.containsKey(id)) {
                map.put(id, new ArrayList<>());
            }
            map.get(id).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Categorize by Count
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        for (String str : strs) {
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(str);
        }
        return new ArrayList<>(ans.values());
    }

    /**
     * use prime number
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        //最多10609个z
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

        HashMap<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int key = 1;
            for (char c : str.toCharArray()) {
                // this could overflow when key is too big
                key *= prime[c - 'a'];
            }
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

}
