package code;

/**
 * https://leetcode.com/problems/rotate-string/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-13 16:22
 */
public class RotateString {

    /**
    public boolean rotateString(String A, String B) {
        String temp = shift(A);
        while(!temp.equals(A)) {
            if(B.equals(temp)) {
                return true;
            }
            temp = shift(temp);
        }
        return false;
    }

    private String shift(String str) {
        return str.substring(1) + str.charAt(0);
    }
     */

    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }

    public static void main(String[] args) {
        RotateString test = new RotateString();

        String A = "abcde", B = "cdeab";
        System.out.println(test.rotateString(A, B));

    }
}
