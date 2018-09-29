package com.leetcode.dp;

public class MinimumSizeSubarraySum209 {
    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        int[] dp = new int[length + 1];
        for (int i = 0; i < length; i++) {
            dp[i + 1] = dp[i] + nums[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= length; j++){
                if (dp[j] - dp[i] >= s) {
                    if (ans > j - i) {
                        ans = j - i;
                    }
                }
            }
        }
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        return ans;
    }

    public int minSubArrayLen01(int s, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = Math.min(ans, i + 1 - left);
                sum -= nums[left];
            }
        }
        return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }
}
