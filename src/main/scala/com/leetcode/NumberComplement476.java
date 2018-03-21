package com.leetcode;

public class NumberComplement476 {
    public int[] binary(int num){
        int[] nums = new int[33];
        int index = 0;
        while (num > 0){
            nums[index++] = num % 2 == 1 ? 0 : 1;
            num = num / 2;
        }
        return nums;
    }
    public int findComplement(int num) {
        int[]nums = binary(num);
        int sum = 0;
        for (int i = 0; i < 33; i++){
            sum = sum + (int)Math.pow(2, i) * nums[i];
        }
        return sum;

    }
}
