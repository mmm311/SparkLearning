package com.niuke.wangyi;

import java.util.Scanner;

public class Problem5 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            long x = in.nextLong();
            long f = in.nextLong();
            long d = in.nextLong();
            long p = in.nextLong();

            int days = Math.min((int)((f * p + d )/ (x + p)) , (int) (d / x));
            System.out.println(days);
        }
    }
}
