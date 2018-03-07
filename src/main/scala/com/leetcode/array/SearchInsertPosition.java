package com.leetcode.array;

/**
 * @author
 * 利用二分法找出插入位置
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target){
        int low = 0, high = nums.length - 1;
        while (low <= high){
            int mid =  low + (high - low) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return low;
     }
}
