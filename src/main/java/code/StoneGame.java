package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/stone-game/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-15 16:20
 */
public class StoneGame {

    /**
     * Recursive
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        Map<String, Boolean> map = new HashMap<>();
        return util(piles, 0, piles.length - 1, 0, 0, map);
    }

    private Boolean util(int[] piles, int start, int end, int alex, int lee, Map<String, Boolean> map) {
        if (end < start) {
            return alex > lee;
        }
        String key = start + "," + end;
        if ((start + end) % 2 == 0) {
            if (!map.containsKey(key)) {
                Boolean val = util(piles, start + 1, end, alex + piles[start], lee, map)
                        || util(piles, start, end - 1, alex + piles[end], lee, map);
                map.put(key, val);
            }
            return map.get(key);
        } else {
            if (!map.containsKey(key)) {
                Boolean val = util(piles, start + 1, end, alex, lee + piles[start], map)
                        || util(piles, start, end - 1, alex, lee + piles[end], map);
                map.put(key, val);
            }
            return map.get(key);
        }
    }

    /**
     * Alex is first to pick pile.
     * piles.length is even, and this lead to an interesting fact:
     * Alex can always pick odd piles or always pick even piles!
     * <p>
     * For example,
     * If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
     * he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
     * Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.
     * <p>
     * In the description, we know that sum(piles) is odd.
     * If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
     * If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.
     * <p>
     * So, Alex always defeats Lee in this game.
     *
     * @param piles
     * @return
     */
    public boolean stoneGame1(int[] piles) {
        return true;
    }

    /**
     * 2D DP
     * <p>
     * dp[i][j] means the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
     * You can first pick piles[i] or piles[j].
     * <p>
     * If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
     * If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
     * <p>
     * So we get:
     * dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
     * We start from smaller subarray and then we use that to calculate bigger subarray.
     *
     * for [5,3,4,5], the final dp matrix is :
     *
     * 5   2   4   1
     * 0   3   1   4
     * 0   0   4   1
     * 0   0   0   5
     *
     * @param piles
     * @return
     */
    public boolean stoneGame2(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            //只有一堆的情况
            dp[i][i] = piles[i];
        }

        for (int range = 1; range < len; range++) {
            for (int i = 0; i < len - range; i++) {
                //从基本情况开始给dp[][]赋值
                dp[i][i + range] = Math.max(piles[i] - dp[i + 1][i + range], piles[i + range] - dp[i][i + range - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }

    /**
     * 1D DP
     *
     * @param piles
     * @return
     */
    public boolean stoneGame3(int[] piles) {
        int len = piles.length;
        int[] dp = Arrays.copyOf(piles, len);
        for (int d = 1; d < len; d++) {
            for (int i = 0; i < len - d; i++) {
                dp[i] = Math.max(piles[i] - dp[i + 1], piles[i + d] - dp[i]);
            }
        }
        return dp[0] > 0;
    }

}
