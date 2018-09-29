package com.niuke.wangyi;

import java.util.Scanner;

public class Problem01 {
    public static void main(String[] args){
        Scanner in  = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(j + " ");
            }
            System.out.print(i + " ");
            for (int j = i - 1; j >= 1; j--) {
                System.out.print(j  + " ");
            }
            System.out.println();
        }
    }
}
