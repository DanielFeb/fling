package indi.daniel.fling.basic;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        Producer producer = new Producer("Producer1");
        executorService.execute(producer);
        executorService.execute(new Consumer("Consumer1", producer));
        executorService.execute(new Consumer("Consumer2", producer));

    }
    public static class Consumer implements Runnable {
        private String name;
        private Producer producer;

        public Consumer(String name, Producer producer) {
            this.name = name;
            this.producer = producer;
        }

        public void run() {
            System.out.println(name + " parked!");
            producer.addObserver(Thread.currentThread());
            LockSupport.park(this);
            System.out.println(name + " unparked!");

        }
    }
    public static class Producer implements Runnable {
        private String name;
        private Queue<Thread> observerQueue = new LinkedBlockingQueue<>();
        public void addObserver(Thread waitNode) {
            observerQueue.add(waitNode);
        }


        public Producer(String name) {
            this.name = name;
        }

        public void run() {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(observerQueue.poll());
            }

        }
    }
}
