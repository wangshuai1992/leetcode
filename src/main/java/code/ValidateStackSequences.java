package code;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/validate-stack-sequences/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-26 09:28
 */
public class ValidateStackSequences {

    /**
     * keep push until stack.peek() has the same value of popped`s head
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //push index
        int i = 0;
        //pop index
        int j = 0;
        int len = pushed.length;

        Deque<Integer> stack = new ArrayDeque<>();
        while (!(i >= len && stack.isEmpty())) {
            if (stack.isEmpty()) {
                stack.push(pushed[i++]);
            }
            while (!stack.peek().equals(popped[j]) && i < len) {
                stack.push(pushed[i++]);
            }
            if (!stack.peek().equals(popped[j])) {
                return false;
            }
            stack.pop();
            j++;
        }
        return j >= len;
    }

    /**
     * keep pop until stack.peek() has different value of pop`s head
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        int len = pushed.length;

        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < len && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == len;
    }

}
