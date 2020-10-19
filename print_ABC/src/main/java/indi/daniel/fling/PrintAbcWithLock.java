package indi.daniel.fling;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAbcWithLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(3);
        Thread tA = new PrintThread("A", lock, latch);
        Thread tB = new PrintThread("B", lock, latch);
        Thread tC = new PrintThread("C", lock, latch);
        tA.start();
        tB.start();
        tC.start();
        latch.await();
        System.out.println("");
        System.out.println("success");
    }

    public static class PrintThread extends Thread {
        public static int state = 0;
        private Lock lock;
        private CountDownLatch latch;
        public PrintThread(String name, Lock lock, CountDownLatch latch) {
            super(name);
            this.lock = lock;
            this.latch = latch;

        }

        private static int LOOP_COUNT=100;

        @Override
        public void run() {
            for (int i = 0; i < LOOP_COUNT; i++) {
                while(true) {
                    lock.lock();
                    try{
                        if (state % 3 == (getName().charAt(0) - 'A')) {
                            System.out.println(getName() + i);
                            state ++;
                            break;
                        }
                        sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
            this.latch.countDown();
        }
    }
}
