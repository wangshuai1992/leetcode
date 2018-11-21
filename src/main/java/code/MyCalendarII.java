package code;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-20 15:23
 */
public class MyCalendarII {

    /**
     * brute force
     */
    class MyCalendarTwo {

        List<int[]> calendar;
        List<int[]> overlaps;

        MyCalendarTwo() {
            calendar = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] iv : overlaps) {
                if (iv[0] < end && start < iv[1]) {
                    return false;
                }
            }
            for (int[] iv : calendar) {
                if (iv[0] < end && start < iv[1]) {
                    overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
                }
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }

    /**
     * use treemap / Boundary Count
     *
     * When booking a new event [start, end), count delta[start]++ and delta[end]--.
     * When processing the values of delta in sorted order of their keys, the running sum active is
     * the number of events open at that time. If the sum is 3 or more, that time is (at least) triple booked.
     *
     */
    class MyCalendarTwo1 {
        TreeMap<Integer, Integer> delta;

        public MyCalendarTwo1() {
            delta = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0;
            for(int value : delta.values()) {
                active += value;
                if(active >= 3) {
                    delta.put(start, delta.get(start) - 1);
                    delta.put(end, delta.get(end) + 1);
                    return false;
                }
            }
            return true;
        }
    }

}
