package com.ppd;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Test extends Thread {

    public static void main(String[] args) throws Exception {
        String str = "abcdefghijklmnop";
        int length = str.length();
        int size = (length + 4) / 4;
        System.out.println(size);
        char [][]result = new char[size][size];
        for (int i = 0; i < length; i++){
           if (i / (size - 1) == 0){
               result[0][i] = str.charAt(i);
           }
           if (i / (size - 1) == 1){
               result[i % (size - 1)][size - 1] = str.charAt(i);
           }
           if (i / (size - 1) == 2){
               result[size - 1][size -1 - (i % (size - 1))] = str.charAt(i);
           }else{
               result[size - 1 - (i % (size - 1))][0] = str.charAt(i);
           }
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(result[i][j]);
            }
            System.out.println();

        }
    }
}
