package com.niuke.jianzhioffer;

/*
重建二叉树
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Problem04 {
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        int length  = pre.length;

        if (length == 0 || length < 0) {
            return null;
        }
        return conStructCore(pre, 0, length - 1,
                in, 0, length - 1);
    }

    public static TreeNode conStructCore(int[] pre, int pre_start, int pre_end,
                                  int[] in, int in_start, int in_end) {
        int rootValue = pre[pre_start];
        TreeNode root = new TreeNode(rootValue);
        root.left = root.right = null;

        if (pre_start == pre_end) {
            if (in_start == in_end && pre_start == in_start) {
                return root;
            }
        }

        int rootInorder = in_start;
        while (rootInorder <= in_end && in[rootInorder] != rootValue) {
            rootInorder++;
        }

        int leftLength = rootInorder - in_start;
        int leftPre_end = pre_start + leftLength;

        if (leftLength > 0) {
            root.left = conStructCore(pre, pre_start + 1, leftPre_end,
                    in, in_start, rootInorder - 1);
        }

        if (leftLength < pre_end - pre_start) {
            root.right = conStructCore(pre, leftPre_end + 1,
                    pre_end, in,rootInorder + 1, in_end);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] pre = new int[] {1,2,3,4,5,6,7};
        int[] in = new int[] {3,2,4,1,6,5,7};
        TreeNode root = reConstructBinaryTree(pre, in);
    }

}
