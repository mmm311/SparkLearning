package com.leetcode.dp;

public class MinCostClimbingStairs746 {

    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0, f2 = 0;
        int length = cost.length;

       for (int i = 0; i < length; i++) {
           int f0 = cost[i] + Math.min(f1, f2);
           f2 = f1;
           f1 = f0;
       }

        return Math.min(f1, f2);
    }
}
