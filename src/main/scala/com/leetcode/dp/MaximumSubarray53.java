package com.leetcode.dp;

public class MaximumSubarray53 {

    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int i = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int j = 0; j < length; j++) {
            if (sum + nums[j] <= nums[j]) {
                i = j;
                sum = nums[j];
            }else {
                sum = sum + nums[j];
            }

            if (max < sum) {
                max = sum;
            }

        }
        return max;
    }
}
