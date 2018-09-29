package com.test;

class  Person{
    {
        System.out.println("P1");
    }
    static{
        System.out.println("P2");
    }
    public Person(){
        System.out.println("P3");
    }
}
public  class  Students extends Person{
    static{
        System.out.println("S1");
    }
    {
        System.out.println("S2");
    }
    public Students(){
        System.out.println("S3");
    }
    public static void  main(String[] args){
        new Students();
    }
}
