package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/jewels-and-stones/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-13 14:43
 */
public class JewelsAndStones {

    /**
    public int numJewelsInStones(String J, String S) {
        char[] js = J.toCharArray();
        int neighborCount = 0;
        for (char j : js) {
            int index = 0;
            int temp;
            while ((temp = S.indexOf(j, index)) > -1) {
                neighborCount++;
                index = ++temp;
            }
        }
        return neighborCount;
    }
     */

    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();

        int count = 0;

        for(char c : J.toCharArray()) {
            set.add(c);
        }

        for(char c : S.toCharArray()) {
            //hashset add、remove 及contains 方法的时间复杂度是一个常量 O(1)
            count += set.contains(c) ? 1 : 0;
        }

        return count;
    }

    public static void main(String[] args) {
        String J = "aA";
        String S = "aAAbbbb";
        System.out.println(new JewelsAndStones().numJewelsInStones(J, S));
    }

}
