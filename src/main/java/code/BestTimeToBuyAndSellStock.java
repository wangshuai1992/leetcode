package code;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 17:25
 */
public class BestTimeToBuyAndSellStock {

    /**
     * Brute Force / O(n^2) time
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = Integer.MIN_VALUE;

        for(int i = 0; i < prices.length - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                profit = Math.max(profit, prices[j] - prices[i]);
            }
        }
        return profit > 0 ? profit : 0;
    }

    /**
     * One Pass / O(n) time
     *
     * 计算每个 “谷 - 峰 区间”的收益 并 比较记录最大值
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int profit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int p : prices) {
            if (p < minPrice) {
                minPrice = p;
            } else {
                profit = Math.max(profit, p - minPrice);
            }
        }
        return profit;
    }

}
