package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-04-11 14:21
 */
public class MinStack {

    private int min;

    private Deque<Integer> stack;

    public MinStack() {
        min = Integer.MAX_VALUE;
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if(x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if(stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

}
