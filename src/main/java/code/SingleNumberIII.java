package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/single-number-iii/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-02 17:18
 */
public class SingleNumberIII {

    public int[] singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int n : nums) {
            if (map.get(n) == null) {
                map.put(n, 1);
            } else {
                map.put(n, 2);
            }
        }

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result[index++] = entry.getKey();
            }
        }
        return result;
    }

    /**
     * O(n)-time , O(1)-space
     * https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C++Java-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations
     *
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) {
                // the bit is not set
                rets[0] ^= num;
            } else {
                // the bit is set
                rets[1] ^= num;
            }
        }
        return rets;
    }

    public static void main(String[] args) {
        int num = 244;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(-num));
        System.out.println(Integer.toBinaryString(num&-num));
    }

}
