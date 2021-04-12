package code;

/**
 * https://leetcode.com/problems/implement-strstr/
 * https://blog.csdn.net/weixin_43314519/article/details/107444415
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-11-09 18:57
 */
public class ImplementStrStr {

    /**
     * brute force / O(n) time
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int l1 = haystack.length();
        int l2 = needle.length();
        for (int i = 0; i < l1; i++) {
            for (int j = 0; ; j++) {
                if (j == l2) {
                    return i;
                }
                if (i + j == l1) {
                    // 提前退出
                    return -1;
                }
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
        }
        return -1;
    }

    /**
     * KMP / O(M + N) time
     * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
     * https://www.zhihu.com/question/21923021
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        char[] needleChars = needle.toCharArray();
        char[] haystackChars = haystack.toCharArray();
        int[] pai = new int[needleChars.length];
        pai[0] = -1;
        int k = -1;
        for (int i = 1; i < needleChars.length; i++) {
            while (k > -1 && needleChars[k + 1] != needleChars[i]) {
                k = pai[k];
            }
            if (needleChars[k + 1] == needleChars[i]) {
                k++;
            }
            pai[i] = k;

        }
        k = -1;
        for (int i = 0; i < haystackChars.length; i++) {
            while (k > -1 && needleChars[k + 1] != haystackChars[i]) {
                k = pai[k];
            }
            if (needleChars[k + 1] == haystackChars[i]) {
                k++;
                if (k == needleChars.length - 1) {
                    return i - k;
                }
            }
        }
        return -1;
    }

    /**
     * Rabin–Karp / O(n) time, O(1) space
     *
     * https://juejin.im/entry/6844903638490415111
     * https://juejin.im/post/6844903503333326855
     *
     * @param source
     * @param target
     * @return
     */
    public int strStr2(String source, String target) {
        if (source == null || target == null || source.length() < target.length()) {
            return -1;
        }
        if (target.length() == 0) {
            return 0;
        }

        final int MAGIC_NUM = 31;
        final int MODE = 1000007;

        int highestPower = 1;
        for (int i = 0; i < target.length(); i++) {
            highestPower = (highestPower * MAGIC_NUM) % MODE;
        }

        // init sourceHash and targetHash
        int sourceHash = 0;
        int targetHash = 0;
        for (int i = 0; i < target.length(); i++) {
            sourceHash = (((sourceHash + source.charAt(i)) % MODE) * MAGIC_NUM) % MODE;
            targetHash = (((targetHash + target.charAt(i)) % MODE) * MAGIC_NUM) % MODE;
        }

        // "i + (target.length() - 1) < source.length()" is for limit of "i + j"
        for (int i = 0; i + (target.length() - 1) < source.length(); i++) {
            //update sourceHash
            if (i - 1 >= 0) {
                // for this problem, pre-calculating highestPower is necessary to avoid TLE...
                int minus = (source.charAt(i - 1) * highestPower) % MODE;
                sourceHash = (sourceHash + (MODE - minus)) % MODE;
                sourceHash = (((sourceHash + source.charAt(i + target.length() - 1)) % MODE) * MAGIC_NUM) % MODE;
            }
            //judge
            if (sourceHash == targetHash) {
                for (int j = 0; j < target.length(); j++) {
                    if (source.charAt(i + j) != target.charAt(j)) {
                        return -1;
                    }
                }
                return i;
            }
        }
        return -1;
    }

}
