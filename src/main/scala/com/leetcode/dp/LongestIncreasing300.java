package com.leetcode.dp;

import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestIncreasing300 {

    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int[] dp = new int[length];
        dp[0] = 1;

        int maxans = 0;
        for (int i = 0; i < length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(dp[i], maxans);
        }
        return maxans;
    }
}
