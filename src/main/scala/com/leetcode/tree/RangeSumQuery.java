package com.leetcode.tree;

public class RangeSumQuery {
    private SegmentTree root;
    public RangeSumQuery (int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    class SegmentTree{
        int start, end, value;
        SegmentTree left, right;

        public SegmentTree(int start, int end){
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.value = Integer.MIN_VALUE;
        }
    }

    private SegmentTree buildTree(int[] nums, int start, int end){
        if (start > end){
            return null;
        }else{
            SegmentTree node = new SegmentTree(start, end);
            if (start == end){
                node.value = nums[start];
            }else{
                int mid = (start + end) / 2;
                node.left = buildTree(nums, start, mid);
                node.right = buildTree(nums, mid + 1, end);
                node.value = node.left.value + node.right.value;
            }
            return node;
        }
    }

    private int sumRange(SegmentTree root, int left, int right){
       if (root.start == left && root.end == right){
           return root.value;
       }else{
           int mid = (root.start + root.end) / 2;
           if ( right <= mid){
               return sumRange(root.left, left, right);
           }else if (mid + 1 <= left){
               return sumRange(root.right, left, right);
           }else{
               return sumRange(root.left, left, mid) + sumRange(root.right, mid + 1, right);
           }
       }

    }
}
