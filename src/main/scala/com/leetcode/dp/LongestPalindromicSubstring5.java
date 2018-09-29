package com.leetcode.dp;

public class LongestPalindromicSubstring5 {

    public String longestPalindrome(String s) {

        if ("".equals(s)) {
            return "";
        }

        String orgin = s;
        String reverse = new StringBuilder(s).reverse().toString();
        int length = s.length();
        int [][] arr = new int[length][length];

        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (orgin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    }else{
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }

                if (arr[i][j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[i][j] - 1 == i) {
                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);

    }
}
