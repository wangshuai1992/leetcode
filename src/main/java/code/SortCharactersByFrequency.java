package code;

import java.util.*;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 11:06
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, List<Character>> map1 = new TreeMap<>((o1, o2) -> o2 - o1);
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int feq = entry.getValue();
            if(map1.get(feq) != null) {
                map1.get(feq).add(c);
            } else {
                List<Character> list = new ArrayList<>();
                list.add(c);
                map1.put(feq, list);
            }
        }

        StringBuilder builder = new StringBuilder();
        for(Map.Entry<Integer, List<Character>> entry : map1.entrySet()) {
            for(char c : entry.getValue()) {
                for(int i=0; i<entry.getKey(); i++) {
                    builder.append(c);
                }
            }

        }
        return builder.toString();
    }

    public String frequencySort1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character>[] bucket = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int frequency = entry.getValue();
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >=0; pos--) {
            if (bucket[pos] != null) {
                for (char num : bucket[pos]) {
                    for (int i = 0; i < pos; i++) {
                        sb.append(num);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * normal way using PriorityQueue
     *
     * @param s
     * @return
     */
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int)e.getValue(); i++) {
                sb.append(e.getKey());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SortCharactersByFrequency().frequencySort("tree"));
    }

}
