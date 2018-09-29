package com.niuke.touxiao;

import java.util.Scanner;

public class Problem03 {

    public static int[] sort(int []array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (array[j] <= array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j -1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] values = str.split(",");
        int[] array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.valueOf(values[i]);
        }
        int[] new_array = sort(array);
        int i = 0;
        for (i = 0; i < new_array.length - 1; i++) {
            System.out.print(new_array[i] + ",");
        }
        System.out.print(new_array[i]);
    }
}
