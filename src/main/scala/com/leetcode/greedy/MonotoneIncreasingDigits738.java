package com.leetcode.greedy;

public class MonotoneIncreasingDigits738 {

    public int monotoneIncreasingDigits(int N) {


        char[] digits = String.valueOf(N).toCharArray();

        int mark = digits.length;
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] < digits[i - 1]) {
                mark = i - 1;
                digits[i - 1]--;
            }
        }
        for (int i = mark + 1; i < digits.length; i++) {
            digits[i] = '9';
        }

        return Integer.parseInt(new String(digits));
    }
}
