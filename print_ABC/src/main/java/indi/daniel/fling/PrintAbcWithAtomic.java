package indi.daniel.fling;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAbcWithAtomic {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Thread tA = new PrintThread("A", latch);
        Thread tB = new PrintThread("B", latch);
        Thread tC = new PrintThread("C", latch);
        tA.start();
        tB.start();
        tC.start();
        latch.await();
        System.out.println("");
        System.out.println("success");
    }

    public static class PrintThread extends Thread {
        public static AtomicInteger state = new AtomicInteger(0);
        private CountDownLatch latch;
        public PrintThread(String name, CountDownLatch latch) {
            super(name);
            this.latch = latch;

        }

        private static int LOOP_COUNT=100;

        @Override
        public void run() {
            for (int i = 0; i < LOOP_COUNT; i++) {
                while(true) {
                    if (state.get() % 3 == (getName().charAt(0) - 'A')) {
                        System.out.println(getName() + i);
                        state.addAndGet(1);
                        break;
                    }
                }
            }
            this.latch.countDown();
        }
    }
}
