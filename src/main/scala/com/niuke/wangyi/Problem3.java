package com.niuke.wangyi;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String s = in.nextLine();
            int length = s.length();
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++){
                dp[i][i] = 1;
            }
            int max = 0;
            for (int i = 0; i < length; i++){
                for (int j = i + 1; j < length; j++){
                    if (s.charAt(j - 1) == s.charAt(j)){
                        max = Math.max(dp[i][j - 1], max);
                    }else{
                        dp[i][j] = dp[i][j - 1] + 1;
                    }

                }

            }
            max = Math.max(max, dp[length - 1][length -  1]);
            System.out.println(max);
        }
    }
}
