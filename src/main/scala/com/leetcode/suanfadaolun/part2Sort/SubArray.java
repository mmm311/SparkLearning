package com.leetcode.suanfadaolun.part2Sort;

public class SubArray {

    public static int find_max_cross_subarray(int[] A, int low, int mid, int high){
        int left_sum = Integer.MIN_VALUE;
        int max_left = 0;
        int sum = 0;
        for(int i = mid; i >= low; i--) {
            sum = sum + A[i];
            if (left_sum < sum){
                left_sum = sum;
                max_left = i;
            }
        }

        int right_sum = Integer.MIN_VALUE;
        int max_right = 0;
        sum = 0;
        for (int j = mid + 1; j <= high; j++){
            sum = sum + A[j];
            if (right_sum < sum){
                right_sum = sum;
                max_right = j;
            }
        }


        return left_sum + right_sum;
    }

    public static int find_maximum_subarray(int[] A, int low, int high){
        if (low == high){

            return A[low];
        }
        else{
            int mid = (low + high) / 2;


            int left_sum  = find_maximum_subarray(A, low, mid);
            int right_sum = find_maximum_subarray(A, low, mid);
            int cross_sum = find_max_cross_subarray(A, low, mid, high);

            if (left_sum > right_sum && left_sum > cross_sum){
                return left_sum;
            }
            else  if (right_sum > left_sum && right_sum > cross_sum){
                return right_sum;
            }else {
                return cross_sum;
            }
        }
    }
    public static int maxSubSum(int[] a) {
        int max_left = 0, max_rigth = 0;
        int maxSum = 0, thisSum = 0;
        if (a == null || a.length <= 0){
            return 0;
        }
        for (int i = 0; i < a.length; i++){
            thisSum += a[i];
            if (thisSum > maxSum){
                maxSum = thisSum;
                max_rigth = i;
            }else if ( thisSum < 0){
                thisSum = 0;
                max_left = i + 1;
            }

        }
        System.out.println(max_left+ " " + max_rigth);
        return maxSum;
    }

    public static void main(String[] args){
        int []A= {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int left = 0;
        int right = 0;
        int sum = find_maximum_subarray(A, 0, 15);

        System.out.println(sum );
        sum = maxSubSum(A);
        System.out.println(sum);
    }


}
