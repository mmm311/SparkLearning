package com.niuke.wangyi;

import java.util.Scanner;

public class Main02 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a  = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if (a == 1 || b == 1 || c == 1) {
            int sum = a + b + c;
            int max = Math.max(a, Math.max(b, c));
            int mediue = sum -  max - 1;
            System.out.println(mediue * max + max);
        }
        else{
            System.out.println(a * b * c);
        }
    }
}
