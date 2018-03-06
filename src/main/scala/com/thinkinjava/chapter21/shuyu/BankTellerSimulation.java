package com.thinkinjava.chapter21.shuyu;

/**
 * @author liu
 * 银行出纳员仿真
 */

import akka.util.Timeout;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 客户对象
 */
class Customer{
    private final int serviceTime;

    public Customer(int tm){
        this.serviceTime = tm;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString(){
        return "[" + serviceTime + "]";
    }
}

/**
 * 一队客户
 */
class CustomerLine extends ArrayBlockingQueue<Customer>{

    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    @Override
    public String toString(){
        if (this.size() == 0){
            return "[Empty]";
        }

        StringBuilder result = new StringBuilder();
        for (Customer customer: this){
            result.append(customer);
        }
        return result.toString();
    }
}

/**
 * 随机生成一个队列
 */

class CustomerGenerator implements Runnable{

    private CustomerLine customers;
    private static Random rand = new Random(47);

    public CustomerGenerator(CustomerLine cq){
        this.customers = cq;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.put(new Customer(rand.nextInt(1000)));
            }
        }catch (InterruptedException e){
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}

class Teller implements Runnable, Comparable<Teller>{

    private static int counter = 0;
    private final int id = counter ++;

    private int customersServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq){
        this.customers = cq;
    }
    @Override
    public synchronized int compareTo(Teller other) {
        return customersServed < other.customersServed ? -1 : (customersServed == other.customersServed ? 0 : 1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this){
                    customersServed++;
                    while (!servingCustomerLine){
                        wait();
                    }
                }
            }
        }catch (InterruptedException e){
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public synchronized void doSomethingElse(){
        customersServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serveCustomerLine(){
        assert !servingCustomerLine:"already serving：" + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString(){
        return "Teller" + id +" ";
    }

    public String shortString(){
        return "T" + id;
    }

}

class TellerManager implements Runnable{

    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private Queue<Teller> tellersDoingOtherThing = new LinkedList<>();
    private int adjustmentPeriod;
    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e, CustomerLine customers, int adjustmentPeriod){
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumer(){
        if (customers.size() / workingTellers.size() > 2){
            if (tellersDoingOtherThing.size() > 0){
                Teller teller = tellersDoingOtherThing.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }

        if(workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2){
            if (customers.size() == 0){
                while (workingTellers.size() > 1){
                    reassignOneTeller();
                }
            }
        }

    }

    public void reassignOneTeller(){
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThing.offer(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumer();
                System.out.print(customers + " {");
                for(Teller teller: workingTellers){
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        }catch (InterruptedException e){
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    @Override
    public String toString(){
        return "TellerManager";
    }
}
public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        executorService.execute(new CustomerGenerator(customers));
        executorService.execute(new TellerManager(executorService, customers, ADJUSTMENT_PERIOD));
        TimeUnit.MILLISECONDS.sleep(100000);
        executorService.shutdown();
    }
}
