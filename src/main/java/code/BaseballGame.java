package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/baseball-game/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 17:00
 */
public class BaseballGame {

    public int calPoints(String[] ops) {
        List<Integer> list = new ArrayList<>();

        for(String op : ops) {
            switch (op) {
                case "+" :
                    list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
                    break;
                case "D" :
                    list.add(list.get(list.size() - 1) * 2);
                    break;
                case "C" :
                    list.remove(list.get(list.size() - 1));
                    break;
                default :
                    list.add(Integer.parseInt(op));
            }
        }

        return list.stream().mapToInt(value -> value).sum();
    }

}
