package com.leetcode.tree;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}
public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSumRecursive(TreeNode root){
        if (root == null) {
            return 0;
        }

        int left = Math.max(maxPathSumRecursive(root.left), 0);
        int right = Math.max(maxPathSumRecursive(root.right), 0);

        max = Math.max(max, root.val + left + right);
        return root.val + Math.max(left, right);

    }
    public int maxPathSum(TreeNode root){
        maxPathSumRecursive(root);
        return max;
    }
}
