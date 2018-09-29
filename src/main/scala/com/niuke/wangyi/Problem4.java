package com.niuke.wangyi;

import java.util.Scanner;

public class Problem4 {
    public static void print(int[] nums){
        for (int i = 0; i < nums.length - 1; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println(nums[nums.length - 1]);
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int []nums = new int[n];
            for (int i = 0; i < n; i++){
                nums[i] = in.nextInt();
            }
            print(nums);
            int [] dp = new int[n];
            if (n == 1){
                print(nums);
            }
            int left = n - 1;
            int right = n - 2;

           if (n % 2 == 0){
               dp[n / 2] = nums[0];
               dp[n / 2 - 1] =nums[1] ;
               // 左边
               for (int i = 0; i < n / 2 - 1; i++){
                   if (left >= 3) {
                       dp[i] = nums[left];
                       left = left - 2;
                   }
               }
               // 右边
               for (int i = n - 1; i > n / 2; i--){
                   if (right >= 2){
                       dp[i] = nums[right];
                       right = right - 2;
                   }
               }
           }else{
                dp[n / 2] = nums[0];
                dp[n / 2 + 1] = nums[1];
                // 左边
               for (int i = 0; i < n / 2; i++){
                   if (left >= 2){
                       dp[i] = nums[left];
                       left = left - 2;
                   }
               }
               for (int i = n - 1; i > n / 2; i--){
                   if (right >= 3){
                       dp[i] = nums[right];
                       right = right - 2;
                   }
               }
           }
           print(dp);
        }
    }
}
