package com.leetcode.tree;

/**
 * @author liu
 */
public class NumArray {
    private SegmentTreeNode root = null;

    public NumArray(int[] nums){
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val){
        update(root, i, val);

    }

    public int sumRange(int i, int j){
        return sumRange(root, i, j);
    }

    /**
     * 建树
     * @param nums 数组
     * @param start 开始位置
     * @param end 结束位置
     * @return SegmentTreeNode 根节点
     */
    private SegmentTreeNode buildTree(int[] nums, int start, int end){
        if (start > end){
            return null;
        }else{
            SegmentTreeNode ret = new SegmentTreeNode(start, end);
            if (start == end){
                ret.sum = nums[start];
            }else{
                int mid = start + (end - start) / 2;
                ret.left = buildTree(nums, start, mid);
                ret.right = buildTree(nums, mid + 1, end);
                ret.sum = ret.left.sum + ret.right.sum;
            }
            return ret;
        }
    }

    /**
     * 单点修改
     * @param root 根节点
     * @param pos 位置
     * @param val 修改值
     */

    private void update(SegmentTreeNode root, int pos, int val){
        if (root.start == root.end){
            root.sum = val;
        }else{
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid){
                update(root.left, pos ,val);
            }else{
                update(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    /**
     * 区间求和
     * @param root 根结点
     * @param start 开始位置
     * @param end 结束位置
     * @return 区间和
     */
    private int sumRange(SegmentTreeNode root, int start, int end){
        if (root.end == end && root.start == start){
            return root.sum;
        }else{
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid){
                return sumRange(root.left, start, end);
            }else if (start >= mid + 1){
                return sumRange(root.right, start, end);
            }else{
                return sumRange(root.right, mid + 1, end) +
                        sumRange(root.left, start, mid);
            }
        }
    }
    class SegmentTreeNode{
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end){
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

}
