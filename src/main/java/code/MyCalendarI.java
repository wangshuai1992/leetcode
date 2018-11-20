package code;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-i/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-20 15:23
 */
public class MyCalendarI {

    /**
     * brute force
     */
    class MyCalendar {
        List<int[]> calendar;

        MyCalendar() {
            calendar = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] iv : calendar) {
                if (iv[0] < end && start < iv[1]) {
                    return false;
                }
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }

    /**
     * use treemap
     */
    class MyCalendar1 {
        /**
         * start为key, end为value. 前一个key的value不大于后一个key
         */
        TreeMap<Integer, Integer> calendar;

        MyCalendar1() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer pre = calendar.floorKey(start);
            Integer next = calendar.ceilingKey(start);
            if ((pre == null || calendar.get(pre) <= start) && (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }

}
