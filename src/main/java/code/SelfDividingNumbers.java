package code;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/self-dividing-numbers/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-29 15:59
 */
public class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new LinkedList<>();

        int num = left;
        while (num <= right) {
            if (validate(num)) {
                result.add(num);
            }
            num++;
        }

        return result;
    }

    private boolean validate(int num) {
        int temp = num;
        int test = temp % 10;

        for ( ; temp != 0; temp /= 10, test = temp % 10) {
            if(test == 0) {
                return false;
            }
            if (num % test != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SelfDividingNumbers().selfDividingNumbers(9, 22));
    }
}
