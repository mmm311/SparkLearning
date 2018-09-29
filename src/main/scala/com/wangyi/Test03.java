package com.wangyi;

import java.util.Scanner;

public class Test03 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String s = in.nextLine();

            int length = s.length();
            Character[] navi = new Character[length ];
            int lNum = 0, rNum = 0;
            if (length > 0 ){
                if (s.charAt(0) == 'L'){
                    navi[0] = 'E';
                }else{
                    navi[0] = 'W';
                }
            }
            for (int i = 1; i < s.length(); i++){
                if (s.charAt(i) == 'L'){
                     if (navi[i - 1] == 'E'){
                         navi[i] = 'N';
                     }else if (navi[i - 1] == 'W'){
                         navi[i] = 'S';
                     }else if (navi[i - 1] == 'N'){
                         navi[i] = 'W';
                     }else {
                         navi[i] = 'E';
                     }
                }else {
                    if (s.charAt(i) == 'R'){
                        if (navi[i - 1] == 'E'){
                            navi[i] = 'N';
                        }else if (navi[i - 1] == 'W'){
                            navi[i] = 'S';
                        }else  if (navi[i - 1] == 'N'){
                            navi[i] = 'E';
                        }else {
                            navi[i] = 'W';
                        }
                    }
                }
            }
            System.out.println(navi[length - 1]);
        }
    }

}
