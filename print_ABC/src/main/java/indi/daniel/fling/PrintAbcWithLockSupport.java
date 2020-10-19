package indi.daniel.fling;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAbcWithLockSupport {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        PrintThread tA = new PrintThread("A", latch);
        PrintThread tB = new PrintThread("B", latch);
        PrintThread tC = new PrintThread("C", latch);
        tA.setNextThread(tB);
        tB.setNextThread(tC);
        tC.setNextThread(tA);
        tA.start();
        tB.start();
        tC.start();
        LockSupport.unpark(tA);
        latch.await();
        System.out.println("");
        System.out.println("success");
    }

    public static class PrintThread extends Thread {
        private CountDownLatch latch;
        private PrintThread nextThread;
        public PrintThread(String name, CountDownLatch latch) {
            super(name);
            this.latch = latch;

        }

        public void setNextThread(PrintThread nextThread) {
            this.nextThread = nextThread;
        }

        private static int LOOP_COUNT=100;

        @Override
        public void run() {
            for (int i = 0; i < LOOP_COUNT; i++) {
                LockSupport.park();
                System.out.println(getName() + i);
                LockSupport.unpark(nextThread);
            }
            this.latch.countDown();
        }
    }
}
