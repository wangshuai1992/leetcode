package code;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 17:26
 */
public class BestTimeToBuyAndSellStockII {

    /**
     * Peak Valley Approach
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int valley;
        int peak;

        int len = prices.length;
        int i = 0;
        while (i < len - 1) {
            while (i < len - 1 && prices[i] >= prices[i + 1]) {
                i ++;
            }
            valley = prices[i];
            while (i < len - 1 && prices[i] <= prices[i + 1]) {
                i ++;
            }
            peak = prices[i];
            res += peak - valley;
        }
        return res;
    }

    /**
     * 价格已知时交易能获取每一次上涨的利润
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }

}
