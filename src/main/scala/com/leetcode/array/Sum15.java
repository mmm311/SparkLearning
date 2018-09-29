package com.leetcode.array;

import java.util.*;

public class Sum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;

        Arrays.sort(nums);
        for (int i = 0; i + 2 < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int j = i + 1, k = length - 1;

            while (j < k) {
                if (target == nums[j] + nums[k]) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;k--;

                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }else if (target < nums[j] + nums[k]) {
                    k--;
                }else{
                    j++;
                }
            }
        }
        return res;
    }
}
