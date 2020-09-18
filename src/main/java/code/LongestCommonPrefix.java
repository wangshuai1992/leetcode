package code;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-09-18 11:37
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int endIndex = 0;
        String firstStr = strs[0];
        boolean flag = true;
        while (endIndex < firstStr.length() && flag) {
            char c = firstStr.charAt(endIndex);
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                if (str.length() <= endIndex || str.charAt(endIndex) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                endIndex++;
            }
        }
        return strs[0].substring(0, endIndex);
    }

}
