package com.leetcode;

/**
 * @author liu
 */
public class RotateString796 {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}
