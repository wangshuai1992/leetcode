package code;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-16 10:26
 */
public class TopKFrequentElements {

    /**
     * minHeap / O(nlgk)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.merge(n, 1, (a, b) -> a + b);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k || minHeap.peek().getValue() < entry.getValue()) {
                minHeap.offer(entry);
            }
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * bucket sort / O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent1(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if(nums.length == 0) {
            return result;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        int[] data = new int[max - min + 1];
        for (int num : nums) {
            data[num - min]++;
        }
        // 频率为index，数为值
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for(int i = 0; i < data.length; i++){
            if(data[i] > 0){
                if(bucket[data[i]] == null){
                    bucket[data[i]] = new ArrayList<>();
                }
                bucket[data[i]].add(i + min);
            }
        }
        for(int i = nums.length; i >= 0 && result.size() < k; i--){
            if(bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }
        return result;
    }

}
