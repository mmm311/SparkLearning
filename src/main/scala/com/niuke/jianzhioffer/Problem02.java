package com.niuke.jianzhioffer;

public class Problem02 {
    public static String replaceSpace(StringBuffer str){
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++){
            if (str.charAt(i) == ' '){
                sb.append("%20");
            }else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String str = "We Are Happy";
        StringBuffer sb = new StringBuffer(str);
        System.out.print(replaceSpace(sb));
    }
}
