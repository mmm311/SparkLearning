package com.leetcode.dp;

public class HouseRobber198 {

    public int rob(int[] nums) {
        int length = nums.length;
        int rob = 0;
        int notRob = 0;

        for (int i = 0; i < length; i++) {
            // 当前房子偷
            int currob = notRob + nums[i];
            // 当前房子不偷
            notRob = Math.max(notRob, rob);
            rob = currob;
        }

        return Math.max(rob, notRob);
    }
}
