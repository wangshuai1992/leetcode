package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-case-permutation/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-29 10:46
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();

        result.add(S);
        findStrings(S, result, 0);

        return result;
    }

    private void findStrings(String s, List<String> set, int index) {
        if(s == null || "".equals(s) || index == s.length()) {
            return;
        }
        findStrings(s, set, index + 1);

        if(!Character.isDigit(s.charAt(index))) {
            s = new StringBuilder(s).replace(index, index+1, String.valueOf(switchCharCase(s.charAt(index)))).toString();
            set.add(s);
            findStrings(s, set, index + 1);
        }
    }

    private char switchCharCase(char c) {
        if(Character.isUpperCase(c)) {
            return Character.toLowerCase(c);
        } else {
            return Character.toUpperCase(c);
        }
    }

    public static void main(String[] args) {
        String s = "1deE2a";
        System.out.println(new LetterCasePermutation().letterCasePermutation(s));
    }

}
