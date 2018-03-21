package com.leetcode.dp;

public class Minimum712 {
    public int minimumDeleteSum(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int [][] dp = new int[s1Length + 1][s2Length + 1];

        for (int i = s1Length - 1; i >= 0; i--){
            dp[i][s2Length] = dp[i + 1][s2Length] + s1.codePointAt(i);
        }

        for (int j = s2Length - 1; j >= 0; j--){
            dp[s1Length][j] = dp[s1Length][j + 1] + s2.codePointAt(j);
        }

        for (int i = s1Length - 1; i >= 0; i--){
            for (int j = s2Length - 1; j >= 0; j--){
                if (s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i + 1][j + 1];
                }else{
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i),
                            dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String []args){
        new Minimum712().minimumDeleteSum("delete","leet");
    }
}
