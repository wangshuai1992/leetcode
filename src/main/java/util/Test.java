package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Test
 *
 * @author wangshuai143
 * @date 2024/4/18
 */
public class Test {

    /**
     * 有三个字母的字符串，满足以下条件
     * 
     * 对于ABC,一个字母正确，而且位置正确
     * 对于AEF，一个字母正确，但是位置不正确
     * 对于CKA，两个字母正确，但是位置都不正确
     * 对于DEB，没有一个字母正确
     * 对于BDK，一个字母正确，但是位置不正确
     *
     * 求该字符串
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 所有的大写字母不重复组成的三个字母的字符串
        List<String> allList = new ArrayList<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            for (char j = 'A'; j <= 'Z'; j++) {
                for (char k = 'A'; k <= 'Z'; k++) {
                    if (i != j && i != k && j != k) {
                        allList.add(String.valueOf(i) + j + k);
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (String str : allList) {
            char[] chars = str.toCharArray();
            if (test1(chars) && test2(chars) && test3(chars) && test4(chars) && test5(chars)) {
                result.add(str);
            }
        }
        System.out.println(result);
    }

    private static boolean test1(char[] chars) {
        // 对于ABC,只有一个字母正确，而且位置正确
        if (chars[0] == 'A') {
            return chars[1] != 'B' && chars[2] != 'C';
        }
        return (chars[1] == 'B') == (chars[2] != 'C');
    }

    private static boolean test2(char[] chars) {
        // 对于AEF，一个字母正确，但是位置不正确
        char[] aefChars = new char[]{'A', 'E', 'F'};
        // chars和aefChars交集大小为1
        int count = 0;
        for (char c : chars) {
            for (char aefChar : aefChars) {
                if (c == aefChar) {
                    count++;
                }
            }
        }
        if (count != 1) {
            return false;
        }
        // 相同位置不能有一样的
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == aefChars[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean test3(char[] chars) {
        // 对于CKA，两个字母正确，但是位置都不正确
        char[] ckaChars = new char[]{'C', 'K', 'A'};
        // chars和ckaChars交集大小为2
        int count = 0;
        for (char c : chars) {
            for (char ckaChar : ckaChars) {
                if (c == ckaChar) {
                    count++;
                }
            }
        }
        if (count != 2) {
            return false;
        }
        // 位置不正确
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ckaChars[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean test4(char[] chars) {
        // 对于DEB，没有一个字母正确
        char[] debChars = new char[]{'D', 'E', 'B'};
        // chars和debChars交集大小为0
        int count = 0;
        for (char c : chars) {
            for (char debChar : debChars) {
                if (c == debChar) {
                    count++;
                }
            }
        }
        return count == 0;
    }

    private static boolean test5(char[] chars) {
        // 对于BDK，一个字母正确，但是位置不正确
        char[] bdkChars = new char[]{'B', 'D', 'K'};
        // chars和bdkChars交集大小为1
        int count = 0;
        for (char c : chars) {
            for (char bdkChar : bdkChars) {
                if (c == bdkChar) {
                    count++;
                }
            }
        }
        if (count != 1) {
            return false;
        }
        // 位置不正确
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == bdkChars[i]) {
                return false;
            }
        }
        return true;
    }
    
}
