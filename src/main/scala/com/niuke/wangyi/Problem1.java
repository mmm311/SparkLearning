package com.niuke.wangyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String s = in.nextLine();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < s.length(); i++){
                set.add(s.charAt(i));
            }

            if (set.size() == 1){
                System.out.println(1);
            }else if(set.size() == 2){
                System.out.println(2);
            }else{
                System.out.println(0);
            }
        }
    }
}
