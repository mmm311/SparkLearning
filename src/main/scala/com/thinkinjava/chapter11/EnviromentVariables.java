package com.thinkinjava.chapter11;

import java.util.Map;

/**
 * @author liu
 *
 */

//获取环境变量
public class EnviromentVariables {
    public static void main(String[] args){
        for (Map.Entry entry: System.getenv().entrySet()){
            if (entry.getKey().equals( "Path")){

                String[] values = ((String)entry.getValue()).split(";");
                for (String s : values){
                    System.out.println(s);
                }
            }
        }
    }
}
