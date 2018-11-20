package code;

/**
 * https://leetcode.com/problems/di-string-match/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-20 09:27
 */
public class DIStringMatch {

    /**
     * two pointer
     *
     * @param str
     * @return
     */
    public int[] diStringMatch(String str) {
        int[] res = new int[str.length() + 1];

        int left = 0;
        int right = res.length - 1;

        for(int i = 0; i < str.length(); i ++) {
            res[i] = str.charAt(i) == 'I' ? left++ : right--;
        }
        res[res.length - 1] = left;
        return res;
    }

}
