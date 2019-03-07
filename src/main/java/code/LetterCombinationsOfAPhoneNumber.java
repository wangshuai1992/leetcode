package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-07 15:16
 */
public class LetterCombinationsOfAPhoneNumber {

    private static Map<Character, String> phone = new HashMap<Character, String>() {
        private static final long serialVersionUID = 6905904601357474582L;
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    /**
     * backtracking
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder builder, List<String> result) {
        if (index == digits.length()) {
            result.add(builder.toString());
            return;
        }
        char key = digits.charAt(index);
        char[] chars = phone.get(key).toCharArray();
        for (char c : chars) {
            backtrack(digits, index + 1, builder.append(c), result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


}
