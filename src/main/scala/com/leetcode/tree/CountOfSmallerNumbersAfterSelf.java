package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums){
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for (int i = nums.length - 2; i >= 0; i--){
            int count = insertNode(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val){
        int thisCount = 0;
        while (true){
            if (val <= root.val){
                root.count ++;
                if (root.left == null){
                    root.left = new TreeNode(val);
                    break;
                }else{
                    root = root.left;
                }
            }else{
                thisCount += root.count;
                if (root.right == null){
                    root.right = new TreeNode(val);
                    break;
                }else{
                    root = root.right;
                }
            }
        }
        return thisCount;
    }

    class TreeNode{
        TreeNode left, right;
        int val;
        int count = 1;

        public TreeNode(int val){
            this.val = val;
        }
    }
}
