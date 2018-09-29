package com.test;

public class Test17 {
    public static void main(String[] args) {
        String str1 = "welcome";
        String str2 = str1.concat("welcome");
        String subString = str2.substring(3, 5);
        System.out.print(subString);
        System.out.print(subString.startsWith("c"));
    }
}
