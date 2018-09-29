package com.leetcode.array;

import java.util.*;

public class Sum18 {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Set<String>  set = new HashSet<>();
        Arrays.sort(nums);
        int length = nums.length;

        for (int i = 0; i + 3 < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }else {
                int j = i + 1;
                for (; j + 2 < length; j++) {
                    int new_target = target - nums[i] - nums[j];
                    int k = j + 1, l = length - 1;
                    while (k < l) {
                        if (new_target == nums[k] + nums[l]) {
                            System.out.println(new_target);
                            String str = nums[i]+" "+nums[j]+" "+ nums[k]+" "+nums[l];
                            if (!set.contains(str)) {
                                res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                                set.add(str);
                            }
                            k++; l--;
                            while (k < l && nums[k] == nums[k - 1]) {
                                k++;
                            }
                            while (k < l && nums[l] == nums[l + 1]) {
                                l--;
                            }
                        }else if (new_target > nums[k] + nums[l]) {
                            k++;
                        }else {
                            l--;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        fourSum(nums, target);
    }

}
