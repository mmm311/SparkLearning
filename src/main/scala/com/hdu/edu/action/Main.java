package com.hdu.edu.action;
class Wine{
    public int year;
    public int name;

    public Wine(int year, int name){
        this.year = year;
        this.name = name;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException{
        Wine w1 = new Wine(1, 2);
        Wine w2 = (Wine) w1.clone();
        w2.setYear(1982);
        w2.setName(123);
        System.out.print(w1.getYear());
        System.out.print(w1.getName());

    }
}
