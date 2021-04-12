package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2021-04-12 16:44
 */
public class FindMedianFromDataStream {

    private static class MedianFinder {
        private List<Integer> dataList;

        public MedianFinder() {
            dataList = new ArrayList<>();
        }

        public void addNum(int num) {
            int index = 0;
            if (!dataList.isEmpty()) {
                // this can optimize to binary search
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i) >= num) {
                        index = i;
                        break;
                    }
                    if (i == dataList.size() - 1) {
                        index = dataList.size();
                    }
                }
            }
            dataList.add(index, num);
        }

        public double findMedian() {
            int i = dataList.size() / 2;
            if ((dataList.size() & 1) == 0) {
                // even
                return ((double) (dataList.get(i - 1) + dataList.get(i))) / 2;
            }
            return dataList.get(i);
        }
    }

}
