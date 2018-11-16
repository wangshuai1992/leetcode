package code;

import java.util.*;

/**
 * https://leetcode.com/problems/reorder-log-files/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-12 16:20
 */
public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        Map<String, String> letterLogMap = new TreeMap<>();
        List<String> numLogList = new LinkedList<>();

        for(String log : logs) {
            int index = log.indexOf(" ");
            String idWord = log.substring(0, index);
            String words = log.substring(index + 1);
            if(Character.isDigit(words.charAt(0))) {
                numLogList.add(log);
            } else {
                letterLogMap.put(words, idWord);
            }
        }

        List<String> result = new LinkedList<>();
        for(Map.Entry entry : letterLogMap.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        result.addAll(numLogList);
        return result.toArray(new String[]{});
    }

    public String[] reorderLogFiles1(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) {
                    return cmp;
                }
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }

}
