package com.niuke.jianzhioffer;

import java.util.Arrays;

public class Problem06 {
    public int minNumberInRotateArray(int [] array) {
        int length = array.length;

        if (length == 0) {
            return 0;
        }
        Arrays.sort(array);
        return array[0];
    }
}
