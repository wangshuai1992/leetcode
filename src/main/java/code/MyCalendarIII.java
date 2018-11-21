package code;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-iii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-20 15:20
 */
public class MyCalendarIII {

    class MyCalendarThree {

        private TreeMap<Integer, Integer> map;

        public MyCalendarThree() {
            this.map = new TreeMap<>();
        }

        /**
         * When booking a new event [start, end), count delta[start]++ and delta[end]--.
         * When processing the values of delta in sorted order of their keys, the largest such value is the answer.
         *
         * @param start
         * @param end
         * @return
         */
        public int book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);

            int active = 0;
            int max = 0;
            for(int value : map.values()) {
                active += value;
                max = Math.max(active, max);
            }
            return max;
        }
    }

}
