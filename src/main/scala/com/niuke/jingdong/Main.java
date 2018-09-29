package com.niuke.jingdong;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] buckets = line.toCharArray();
        int[] values = new int[buckets.length / 2];
        int sum = 1;
        int left_bucket = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == '(') {
                values[left_bucket] = 1;
            }
            for (int j = i + 1; j < buckets.length; j++){
                if (buckets[j] == '(') {
                    values[left_bucket]++;
                }else{
                    break;
                }
            }
            if (buckets[i] == '(') {
                sum = sum * values[left_bucket];
                left_bucket++;
            }
        }

        System.out.println(sum);
    }
}
