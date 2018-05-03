package code;

/**
 * https://leetcode.com/problems/shortest-distance-to-a-character/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-28 13:54
 */
public class ShortestDistanceToACharacter {

    /*
    public int[] shortestToChar(String S, char C) {
        int len = S.length();
        int[] result = new int[len];
        char[] arr = S.toCharArray();

        for (int i = 0; i < len; i++) {
            char ch = arr[i];
            if (ch != C) {
                continue;
            }

            result[i] = 0;

            for (int temp = i - 1, n = 1; temp >= 0; temp--, n++) {
                //从左边第一个字符开始往左
                if (arr[temp] != C && result[temp] == 0) {
                    //未被赋值
                    result[temp] = n;
                } else if (result[temp] != 0 && result[temp] > n) {
                    //已被赋值但不是最小
                    result[temp] = n;
                } else {
                    break;
                }
            }

            for (int temp = i + 1, n = 1; temp < len; temp++, n++) {
                //往右
                if (arr[temp] != C) {
                    result[temp] = n;
                } else {
                    break;
                }
            }
        }
        return result;
    }
    */


    /**
     * Intuition
     *
     * For each index S[i], let's try to find the distance to the next character C going left, and going right.
     * The answer is the minimum of these two values.
     *
     * Algorithm
     *
     * When going left to right, we'll remember the index prev of the last character C we've seen.
     * Then the answer is i - prev.
     * When going right to left, we'll remember the index prev of the last character C we've seen.
     * Then the answer is prev - i.
     * We take the minimum of these two answers to create our final answer.
     *
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToChar1(String S, char C) {
        int N = S.length();
        int[] ans = new int[N];

        int prev = Integer.MIN_VALUE / 2;   //除2防止溢出
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = i - prev;
        }

        prev = Integer.MAX_VALUE / 2;
        for (int i = N - 1; i >= 0; --i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = Math.min(ans[i], prev - i);
        }

        return ans;
    }

}
