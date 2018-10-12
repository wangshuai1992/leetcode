package code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/groups-of-special-equivalent-strings/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-11 16:12
 */
public class GroupsOfSpecialEquivalentStrings {

    public int numSpecialEquivGroups(String[] arr) {
        Set<String> seen = new HashSet<>();
        for (String s : arr) {
            //字符串所有字符的字母个数统计（分奇偶）
            int[] count = new int[52];
            for (int i = 0; i < s.length(); ++i) {
                count[s.charAt(i) - 'a' + 26 * (i % 2)]++;
            }
            seen.add(Arrays.toString(count));
        }
        return seen.size();
    }

}
