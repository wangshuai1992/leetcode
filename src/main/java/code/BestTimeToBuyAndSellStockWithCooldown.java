package code;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-19 11:32
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    /**
     * State Machine Thinking
     *
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking)
     *
     * meaning                                  how to get into this state
     * s0 -> we can buy or rest (sold)          s2 -> rest -> s0 ; s0 -> rest -> s0
     * s1 -> we can sell or rest (hold)         s0 -> buy -> s1 ; s1 -> rest -> s1
     * s2 -> we can only rest (cooldown)        s1 -> sell -> s2
     *
     * base cases :
     * s0[0] = 0;                               // At the start, you don't have any stock if you just rest
     * s1[0] = -prices[0];                      // After buy, you should have -prices[0] profit.
     * s2[0] = INT_MIN;                         // Lower base case
     *
     * transfer : (s[i] stands for the current max profit)
     * s0[i] = max(s0[i - 1], s2[i - 1]);                  // Stay at s0, or rest from s2
     * s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]);      // Stay at s1, or buy from s0
     * s2[i] = s1[i - 1] + prices[i];                      // Only one way from s1
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        //we can buy or rest
        int[] s0 = new int[len];
        //we can sell or rest
        int[] s1 = new int[len];
        //we can only rest
        int[] s2 = new int[len];

        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = Integer.MIN_VALUE;

        for (int i = 1; i < len; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);
            s2[i] = s1[i - 1] + prices[i];
        }
        //s1 means you hold stock at hand, so it can`t be the answer
        return Math.max(s0[len - 1], s2[len - 1]);
    }

    /**
     * O(1) space dp
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int sell = 0;
        int prevSell = 0;
        int buy = Integer.MIN_VALUE;
        int prevBuy;
        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevSell - price, prevBuy);
            prevSell = sell;
            sell = Math.max(prevBuy + price, prevSell);
        }
        return sell;
    }

}
