package code;

/**
 * https://leetcode.com/problems/remove-outermost-parentheses/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-09-30 14:23
 */
public class RemoveOutermostParentheses {

    public String removeOuterParentheses(String originString) {
        int level = 1;
        int index = 0;
        StringBuilder result = new StringBuilder();
        char[] chars = originString.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            level += chars[i] == '(' ? 1 : -1;
            if(level == 0) {
                result.append(originString, index + 1, i);
                index = i + 1;
            }
        }
        return result.toString();
    }

}
