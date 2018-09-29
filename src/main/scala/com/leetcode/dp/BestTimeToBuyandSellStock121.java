package com.leetcode.dp;

public class BestTimeToBuyandSellStock121 {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        int length = prices.length;

        for (int i = 0; i < length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }else if (maxProfit < prices[i] - minPrice){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
