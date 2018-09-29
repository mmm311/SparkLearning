package com.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Incrementable{
    protected long counter = 0;
    public abstract void increament();
}
class SynchronizingTest extends Incrementable{

    @Override
    public synchronized void increament() {
        ++counter;
    }
}

class LockingTest extends Incrementable{

    private Lock lock = new ReentrantLock();
    @Override
    public void increament() {
        lock.lock();
        try{
            ++counter;
        }finally {
            lock.unlock();
        }
    }
}
public class SimpleMicroBenchmark {
  static long test(Incrementable incrementable){
      long start = System.nanoTime();
      for (long i = 0; i < 1000000000L; i++){
          incrementable.increament();
      }
      return System.nanoTime() - start;
  }

  public static void main(String[] args){
      long synchTime = test(new SynchronizingTest());
      long lockTime = test(new LockingTest());
      System.out.printf("synchronized: %1$10d\n", synchTime);
      System.out.printf("lock        : %1$10d\n", lockTime);
      System.out.printf("Lock/synchronized = %1$.3f",(double) lockTime /(double) synchTime);
  }
}
