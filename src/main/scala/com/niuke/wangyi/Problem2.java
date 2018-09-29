package com.niuke.wangyi;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++){
                nums[i] = in.nextInt();
            }
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            int d = nums[1] - nums[0];
            boolean flag = true;
            for (int i = 1 ; i < n; i++){
                if (nums[i] - nums[i - 1] != d){
                    System.out.println("Impossible");
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println("Possible");
            }

        }

    }
}
