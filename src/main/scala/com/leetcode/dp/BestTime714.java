package com.leetcode.dp;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 */
//public class BestTime714 {
//    public int maxProfit(int[] prices, int fee){
//        int length = prices.length;
//        int[][] profits = new int[length][length];
//        for (int i = 0; i < length; i++){
//            profits[i][i] = 0;
//        }
//
//        for (int l = 1; l < length; l++){
//            for (int i = 0; i < length - l + 1; i++){
//                int j = i + l - 1 ;
//                for (int k = i + 1 ; k < j ; k++){
//                    profits[i][j] = Math.max(profits[i][j], profits[i][k] + profits[k][j]);
//                }
//                profits[i][j] = Math.max(prices[j] - prices[i] - fee, profits[i][j] );
//                System.out.println(i+ " " + j +" value:" + profits[i][j]);
//            }
//        }
//        return profits[0][length - 1];
//    }
//
//    public static void main(String[] args){
//        System.out.println(new BestTime714().maxProfit(new int[]{1,3,2,8,4,9}, 2));
//    }
//}


public class BestTime714{
    public int maxProfit(int[] prices, int fee){
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++){
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}

