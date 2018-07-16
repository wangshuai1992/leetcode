package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-difference/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-20 10:10
 */
public class FindTheDifference {

    public char findTheDifference1(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        for(char c : s.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for(char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            char key = entry.getKey();
            if(null == sMap.get(key)) {
                return key;
            }
            if(sMap.get(key) < tMap.get(key)) {
                return key;
            }
        }
        return ' ';
    }

    public char findTheDifference(String s, String t) {
        int len = s.length();
        char result = t.charAt(len);

        for(int i=0; i<s.length(); i++) {
            result ^= s.charAt(i);
            result ^= t.charAt(i);
        }
        return result;
    }

}
