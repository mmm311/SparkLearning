package com.leetcode.dp;

/**
 * @author liu
 */
public class MinimumCostForTickets983 {
    public int mincostTickets(int[] days, int[] costs) {
        int length = days.length;
        int[] dp = new int[length + 1];

        for (int i = 0; i < days.length; i++) {
            int today = days[i];
            int seven = findPrev(days, i, today, 7);
            int thirty = findPrev(days, i, today, 30);

            dp[i + 1] = dp[i] + costs[0];
            if (seven != i) {
                dp[i + 1] = Math.min(dp[i + 1], dp[seven] + costs[1]);
            }
            if (thirty != i) {
                dp[i + 1] = Math.min(dp[i + 1], dp[thirty] + costs[2]);
            }
        }
        return dp[length];
    }

    int findPrev(int[] days, int i, int today, int length) {
        int targetDay = today - length + 1;
        while (i >= 0 && targetDay <= days[i]) {
            i--;
        }
        return i + 1;
    }
}
