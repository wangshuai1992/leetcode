package code;

/**
 * https://leetcode.com/problems/detect-capital/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 14:55
 */
public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        if(word.length() == 1) {
            return true;
        }

        //如果第一个字母为大写
        if(Character.isUpperCase(word.charAt(0))) {
            boolean allUpper = true;
            boolean allLower = true;
            //从第二个字母开始
            for(int i=1; i<word.length(); i++) {
                char c = word.charAt(i);
                if(Character.isLowerCase(c)) {
                    allUpper = false;
                }
                if(Character.isUpperCase(c)) {
                    allLower = false;
                }
            }
            return allUpper || allLower;
        }

        //剩余情况只有全小写才正确
        boolean isAllLower = true;
        for(int i=0; i<word.length(); i++) {
            if(Character.isUpperCase(word.charAt(i))) {
                isAllLower = false;
                break;
            }
        }
        return isAllLower;
    }

    public boolean detectCapitalUse1(String word) {
        return word.matches("[A-Z][a-z]*|[A-Z]*|[a-z]*");
    }

}
