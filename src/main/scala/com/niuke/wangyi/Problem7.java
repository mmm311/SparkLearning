package com.niuke.wangyi;

import java.util.Arrays;
import java.util.Scanner;

public class Problem7 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int []nums = new int[n];
            for (int i = 0; i < n; i++){
                nums[i] = in.nextInt();
            }

            Arrays.sort(nums);
            int [] line = new int[n];
            int index = 1;
            int i, j ;
            int sum = 0;
            for (i = 0, j = n - 1;i < j ;){
                if (index < n){
                    line[index] = nums[i];
                    index ++;
                    i++;
                }
                if (index < n){
                    line[index] = nums[j];
                    index++;
                    j--;
                }
            }
            line[0] = nums[j];
            for (i = 1; i < n; i++){
                sum += Math.abs(line[i] - line[i - 1]);
            }
            System.out.println(sum);
        }
    }
}
