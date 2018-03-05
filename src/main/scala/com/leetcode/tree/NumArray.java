package com.leetcode.tree;

/**
 * @author liu
 */
public class NumArray {
    private SegmentTree root;

    public NumArray(int[] nums){
        root = buildSegmentTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val){
        update(root, i, val);
    }

    public int sumRange(int i, int j){
        return sumRange(root, i, j);
    }

    class SegmentTree{
        int start, end;
        int sum;
        SegmentTree  left, right;
        public SegmentTree(int start, int end){
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = -1;
        }
    }

    private SegmentTree buildSegmentTree(int[] nums, int start, int end){
        if (start > end){
            return null;
        }else{
            SegmentTree node = new SegmentTree(start, end);
            if (start == end){
                node.sum = nums[start];
            }else{
                int mid = (start + end) / 2;
                node.left = buildSegmentTree(nums, start, mid);
                node.right = buildSegmentTree(nums, mid + 1, end);
                node.sum = node.left.sum + node.right.sum;
            }
            return node;
        }
    }

    private void update(SegmentTree root, int pos, int val){
        if (root.start == root.end){
            root.sum = val;
        }else{
            int mid = (root.start + root.end) / 2;
            if (pos <= mid) {
                update(root.left,  pos, val);
            }else{
                update(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    private int sumRange(SegmentTree root, int start, int end){
       if (root.start == start && root.end == end){
           return root.sum;
       }else{
           int mid = (root.start + root.end) / 2;
           if (end <= mid){
              return sumRange(root.left, start, end);
           }else if( start <= mid + 1){
               return sumRange(root.right, start, end);
           }else {
               return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
           }
       }
    }

}
