package com.leetcode;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}
public class MergeTwoBinaryTrees617 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode t = null;
        return mergeTrees(t, t1, t2);

    }
    public TreeNode mergeTrees(TreeNode t, TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null){
            return null;
        }
        else{
            t = new TreeNode((t1 != null ? t1.val: 0) + (t2 != null ? t2.val : 0));
            t.left = mergeTrees(t, (t1 != null ? t1.left : null), (t2 != null ? t2.left : null));
            t.right = mergeTrees(t, (t1 != null ? t1.right : null), (t2 != null ? t2.right : null));
            return t;
        }
    }
}
