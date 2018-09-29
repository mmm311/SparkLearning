package com.algorithm.stringpattern;

/**
 * @author 1034
 */
public class Kms {

    public static int[] compute_prefix(String prefix) {
        char[] prefix_chars = prefix.toCharArray();
        int length = prefix_chars.length;
        int[] dp = new int[length];
        dp[0] = -1;
        int k = -1;

        for (int q = 1; q < length; q++) {
            while (k > -1 && prefix_chars[k + 1] != prefix_chars[q]) {
                k = dp[k];
            }

            if (prefix_chars[k + 1] == prefix_chars[q]) {
                k = k + 1;
            }
            dp[q] = k;
        }

        return dp;
    }


    public static void kmp_matcher(String t, String p) {
        char[] tchars = t.toCharArray();
        char[] pchars = p.toCharArray();

        int n = tchars.length;
        int m = pchars.length;
        int[] dp = compute_prefix(p);
        int q = -1;
        for (int i = 0; i < n; i++) {
            while (q > -1 && pchars[q + 1] != tchars[i]) {
                q = dp[q];
            }
            if (pchars[q + 1] == tchars[i]) {
                q = q + 1;
            }

            if (q == m - 1) {
                System.out.println("Patter occus with shift " + (i - m));
                System.out.println(t.substring(i - m + 1, i + 1));
                q = dp[q];
            }
        }
    }


    public static void main(String[] args) {
        kmp_matcher("ababababa", "aba");
    }
}
