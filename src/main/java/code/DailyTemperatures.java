package code;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/daily-temperatures/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-03 15:28
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            int today = temperatures[i];
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                if (temperatures[j] > today) {
                    count = j - i;
                    break;
                }
            }
            result[i] = count;
        }
        return result;
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        //从最后一个温度开始循环，并记录对应温度出现的位置
        for (int i = temperatures.length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex)
                    warmerIndex = next[t];
            }
            if (warmerIndex < Integer.MAX_VALUE)
                ans[i] = warmerIndex - i;
            next[temperatures[i]] = i;
        }
        return ans;
    }

    /**
     * use stack, work through T = [73, 74, 75, 71, 69, 72, 76, 73] in reverse order
     *
     * When i = 7, stack = [7 (73)]. ans[i] = 0.
     * When i = 6, stack = [6 (76)]. ans[i] = 0.
     * When i = 5, stack = [5 (72), 6 (76)]. ans[i] = 1.
     * When i = 4, stack = [4 (69), 5 (72), 6 (76)]. ans[i] = 1.
     * When i = 3, stack = [3 (71), 5 (72), 6 (76)]. ans[i] = 2.
     * When i = 2, stack = [2 (75), 6 (76)]. ans[i] = 4.
     * When i = 1, stack = [1 (74), 2 (75), 6 (76)]. ans[i] = 1.
     * When i = 0, stack = [0 (73), 1 (74), 2 (75), 6 (76)]. ans[i] = 1
     *
     * stack only contains indices i
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures3(int[] T) {
        int[] ans = new int[T.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

}
