package com.niuke.wangyi;

import java.util.Scanner;

public class Main01 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] interest_point = new int[n];
        int[] flag = new int[n];
        for (int i = 0; i < n; i++){
            interest_point[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++){
            flag[i] = in.nextInt();
        }
        int []dp = new int[n];
        dp[0] = interest_point[0];
        for (int i = 1; i < n; i++){
            if (flag[i] == 1){
                dp[i] = dp[i - 1] + interest_point[i];
            }else{
                dp[i] = dp[i - 1];
            }
        }

        int max = -1;
        int []dp2 = new int[n];
        for (int i = 0; i < n; i++){
            if (flag[i] == 0){
                dp2[i] = dp[i] + interest_point[i];
            }
            for (int j = 1; j < k; j++){
                if (i + j < n) {
                    dp2[i] = dp2[i] + interest_point[i + j];
                }
            }
            dp2[i] = dp[n - 1] - dp[i] + dp2[i];
            if (max < dp2[i]){
                max = dp2[i];
            }
        }
        for (int i = 0; i < n; i++){
            System.out.print(dp[i] + " ");
        }

        System.out.println(max);
    }
}
