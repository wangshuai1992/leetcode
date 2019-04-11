package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-11 17:04
 */
public class MergeIntervals {

    /**
     * sort and merge / O(nlogn) time , O(1) space
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        // sort by the value of start
        intervals.sort(Comparator.comparingInt(a -> a.start));
        List<Interval> result = new ArrayList<>();

        Interval prev = null;
        for (Interval cur : intervals) {
            if (prev == null) {
                prev = cur;
            } else {
                if (prev.end >= cur.start) {
                    prev = new Interval(prev.start, Math.max(prev.end, cur.end));
                } else {
                    result.add(prev);
                    prev = cur;
                }
            }
        }
        if(prev != null) {
            result.add(prev);
        }
        return result;
    }

    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

    }
}
