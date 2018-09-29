package com.leetcode.dp;

import scala.actors.threadpool.Arrays;

public class PartitiontoKEqualSumSubsets698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int length = nums.length;
        int sum = 0;

        for (int i = 0; i < length; i++) {
            sum = sum + nums[i];
        }
        // 每个子集和应该是整数
        if (sum % k != 0) {
            return false;
        }

        int subSetSum = sum / k;
        Arrays.sort(nums);
        return false;

    }
}
