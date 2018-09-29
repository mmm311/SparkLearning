package com.test;

import java.util.Arrays;
import java.util.Scanner;

public class Test11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T-- > 0){
            int n = scanner.nextInt();
            int X = scanner.nextInt();
            int[] A = new int[n];
            int total = 0;
            int hopeTotal = n * X;
            int index = 0;

            for (int j = 0; j < n; j ++){
                A[j] = scanner.nextInt();
                total += A[j];
            }
            Arrays.sort(A);
            while (total <= hopeTotal){

                total += (100 - A[index]);
                index ++;
            }
            System.out.println(index);
        }
    }
}
