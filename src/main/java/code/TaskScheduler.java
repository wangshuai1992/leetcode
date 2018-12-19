package code;

import java.util.*;

/**
 * https://leetcode.com/problems/task-scheduler/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-18 15:38
 */
public class TaskScheduler {

    /**
     * use Sorting
     *
     * pick the task with most remain number as soon as the cool down is over
     *
     * note that the name of the task is irrelevant
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int cdTrack = 0;
            while (cdTrack <= n) {
                if (map[25] == 0) {
                    break;
                }
                if (cdTrack < 26 && map[25 - cdTrack] > 0) {
                    map[25 - cdTrack]--;
                }
                cdTrack++;
                time++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    /**
     * Using PriorityQueue
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval1(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int freq : map) {
            if (freq > 0) {
                queue.add(freq);
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int cdTrack = 0;
            List<Integer> temp = new ArrayList<>();
            while (cdTrack <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1) {
                        temp.add(queue.poll() - 1);
                    } else {
                        queue.poll();
                    }
                }
                time++;
                if (queue.isEmpty() && temp.isEmpty()) {
                    break;
                }
                cdTrack++;
            }
            queue.addAll(temp);
        }
        return time;
    }

    /**
     * Calculating Idle slots
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int maxVal = map[25] - 1;
        int idleSlots = maxVal * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idleSlots -= Math.min(map[i], maxVal);
        }
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }

    public static void main(String[] args) {
        char[] chars = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(new TaskScheduler().leastInterval(chars, 2));
    }

}
