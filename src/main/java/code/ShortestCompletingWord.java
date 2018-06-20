package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/shortest-completing-word/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-06 15:37
 */
public class ShortestCompletingWord {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                if (map.get(c) == null) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
        }

        String result = null;
        int minimalLen = Integer.MAX_VALUE;

        for (String word : words) {
            boolean isLegal = true;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                boolean flag = true;
                int startIndex = 0;
                String temp = word.toLowerCase();
                for (int i = 0; i < entry.getValue(); i++) {
                    int index = temp.indexOf(entry.getKey(), startIndex);
                    if (index < 0) {
                        flag = false;
                        break;
                    }
                    startIndex = index + 1;
                }
                if (!flag) {
                    isLegal = false;
                    break;
                }
            }
            if (isLegal && word.length() < minimalLen) {
                result = word;
                minimalLen = word.length();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] arr = {"step", "steps", "stripe", "stepple"};
        System.out.println(new ShortestCompletingWord().shortestCompletingWord("1s3 PSt", arr));
    }

}
