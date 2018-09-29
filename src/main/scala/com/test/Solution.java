package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static  List<Integer> finalBouquet(int num, int random, int[] sticks) {
       int[] sticks1 = new int[random];
       int length = sticks.length;
       int[] sticks2 = new int[length - random];
       for (int i = 0; i < random; i++) {
           sticks1[i] = sticks[i];
       }
       for (int i = random; i < length; i++) {
           sticks2[i - random] = sticks[i];
       }
       Arrays.sort(sticks1);
       Arrays.sort(sticks2);
       List result = new ArrayList();
       for (int i = 0; i < random; i++) {
           result.add(sticks1[i]);
       }
       for (int i = length - random - 1; i >= 0; i--) {
           result.add(sticks2[i]);
       }
       return result;
    }

    public static void main(String []args) {
        int[] values = new int[] {5, 7, 11, 46, 23, 16, 10, 8};
        List<Integer> result = finalBouquet(8, 3, values);
        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}
