package code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/keyboard-row/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 14:20
 */
public class KeyboardRow {

    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            for (char c : strs[i].toCharArray()) {
                map.put(c, i);//put <char, rowIndex> pair into the map
            }
        }
        List<String> res = new LinkedList<>();
        for (String w : words) {
            if (w.equals("")) continue;
            int index = map.get(w.toUpperCase().charAt(0));
            for (char c : w.toUpperCase().toCharArray()) {
                if (map.get(c) != index) {
                    index = -1; //don't need a boolean flag.
                    break;
                }
            }
            if (index != -1) res.add(w);//if index != -1, this is a valid string
        }
        return res.toArray(new String[0]);
    }

    /*
    public String[] findWords(String[] wordSet) {
        return Stream.of(wordSet)
                .filter(s -> s.toLowerCase().matches("[qwertyuiop]+|[asdfghjkl]+|[zxcvbnm]+"))
                .toArray(String[]::new);
    }
    */

}
