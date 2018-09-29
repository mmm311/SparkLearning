package com.wangyi;
class A {
    static {
        System.out.println("AS");
    }

    public A() {
        System.out.println("AC");
    }
}

class B extends A {
    static {
        System.out.println("BS");
    }

    public B() {
        System.out.println("BC");
    }
}
public class Test04 {
    public static void main(String[] args) {
        new B();
        new B();
    }
}
