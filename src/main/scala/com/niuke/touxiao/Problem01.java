package com.niuke.touxiao;

import java.util.Scanner;

public class Problem01 {

    public  static boolean isLeangelIP (String ip) {
        String[] ips = ip.split("\\.");
        if (ips.length != 4) {
            return false;
        }

        for (int i = 0; i < ips.length; i++) {
            int value = Integer.valueOf(ips[i]);
            if (value < 0 || value > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ipStr = in.nextLine();
        boolean result = isLeangelIP(ipStr);
        System.out.println(result);
    }

}
