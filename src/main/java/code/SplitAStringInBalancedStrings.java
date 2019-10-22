package code;

/**
 * https://leetcode.com/problems/split-a-string-in-balanced-strings/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-10-21 17:52
 */
public class SplitAStringInBalancedStrings {

    public int balancedStringSplit(String s) {
        int balance = 0;
        int count = 0;
        for(char c : s.toCharArray()) {
            balance += c == 'L' ? 1 : -1;
            if(balance == 0) {
                count++;
            }
        }
        return count;
    }

}
