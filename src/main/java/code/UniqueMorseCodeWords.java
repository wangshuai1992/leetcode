package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-morse-code-words/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-29 17:52
 */
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        String[] table = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for(String str : words) {
            StringBuilder trans = new StringBuilder();
            for(char c : str.toCharArray()) {
                trans.append(table[c - 'a']);
            }
            set.add(trans.toString());
        }
        return set.size();
    }
}
