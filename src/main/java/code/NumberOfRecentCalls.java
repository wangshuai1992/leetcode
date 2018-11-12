package code;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/number-of-recent-calls/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-07 16:08
 */
public class NumberOfRecentCalls {

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
    private class RecentCounter {

        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            while (!queue.isEmpty()) {
                Integer element = queue.peek();
                if (element == null || t - element <= 3000) {
                    break;
                }
                queue.poll();
            }
            queue.add(t);
            return queue.size();
        }
    }

    private class RecentCounter1 {
        TreeSet<Integer> set;

        public RecentCounter1() {
            set = new TreeSet<>();
        }

        public int ping(int t) {
            set.add(t);
            return set.tailSet(t - 3000).size();
        }
    }

}
