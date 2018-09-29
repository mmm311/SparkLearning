package com.thinkinjava.chapter05;
import static com.util.Print.*;

class Shape{
    private void draw() {
        print("shape draw");
    };

    public void erase(){
        print("shape erase");
    }


}

public class Square extends Shape{
    public void draw(){
        print("Square draw");
    }

    public void erase(){
        print("Square erase");
    }

    public static void main(String[] args){
        Square square = new Square();
        square.draw();
        square.erase();
        Shape s = square;
        s.erase();

    }
}


