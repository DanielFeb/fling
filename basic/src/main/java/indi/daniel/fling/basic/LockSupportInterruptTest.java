package indi.daniel.fling.basic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

public class LockSupportInterruptTest {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("Thread start and park");
            LockSupport.park();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread been interrupted");
                System.out.println(Thread.currentThread().isInterrupted());
                Thread.interrupted();
                System.out.println(Thread.currentThread().isInterrupted());
            } else {
                System.out.println("Thread unpark");
                System.out.println(Thread.currentThread().isInterrupted());
            }

        });
        thread.start();
//        thread.start();

//        thread.interrupt();
        LockSupport.unpark(thread);

        Thread.sleep(1000);
    }
}
