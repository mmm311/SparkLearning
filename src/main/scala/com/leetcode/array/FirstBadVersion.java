package com.leetcode.array;

/**
 * 利用二分法找出最先错误版本
 */
public class FirstBadVersion {
    boolean isBadVersion(int version){
        return true;
    }
    public int firstBadVersion(int n) {
       int left = 1;
       int right = n;

       while (left < right){
           int mid = left + right;
           if (isBadVersion(mid)){
               right = mid;
           }else {
               left = mid + 1;
           }
       }
       return left;
    }
}
