package com.thinkinjava.chapter21.waxomatic;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.util.Print.*;

class Car{
    private boolean waxOn = false;

    public synchronized void waxed(){
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed(){
        waxOn = false;
        notifyAll();
    }

    public synchronized  void waitForWaxing() throws InterruptedException{
        while (waxOn == false){
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException{
        while (waxOn == true){
            wait();
        }
    }
}

class WaxOn implements Runnable{
    private Car car;

    public WaxOn(Car c){
        this.car = c;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable{
    private Car car;

    public WaxOff(Car c){
        this.car = c;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                printnb("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
                car.waitForWaxing();
            }
        }catch (InterruptedException e){
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}
public class WaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WaxOff(car));
        service.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
