package indi.daniel.fling.jvm;

import java.util.concurrent.locks.LockSupport;

public class TestThreadBlock {
    public static void main (String[] args) {
        Object lock = new Object();
        Thread a = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread a get lock!");
                // park forever
                LockSupport.park();
            }
        });
        Thread b = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread b get lock!");
            }
        });
        a.start();
        b.start();
        // jstack shows Thread b is "blocked", so the state of the threads is "BLOCKED", when are waiting for monitor (synchronized)


//    "Thread-1" #10 prio=5 os_prio=31 tid=0x00007fe07602b800 nid=0x4503 waiting for monitor entry [0x000070000c467000]
//    java.lang.Thread.State: BLOCKED (on object monitor)
//    at indi.daniel.fling.jvm.TestThreadBlock.lambda$main$1(TestThreadBlock.java:17)
//            - waiting to lock <0x00000007956d2db0> (a java.lang.Object)
//    at indi.daniel.fling.jvm.TestThreadBlock$$Lambda$2/2074407503.run(Unknown Source)
//    at java.lang.Thread.run(Thread.java:745)
//
//            "Thread-0" #9 prio=5 os_prio=31 tid=0x00007fe07602a800 nid=0x4703 waiting on condition [0x000070000c364000]
//    java.lang.Thread.State: WAITING (parking)
//    at sun.misc.Unsafe.park(Native Method)
//    at java.util.concurrent.locks.LockSupport.park(LockSupport.java:304)
//    at indi.daniel.fling.jvm.TestThreadBlock.lambda$main$0(TestThreadBlock.java:12)
//            - locked <0x00000007956d2db0> (a java.lang.Object)
//    at indi.daniel.fling.jvm.TestThreadBlock$$Lambda$1/1149319664.run(Unknown Source)
//    at java.lang.Thread.run(Thread.java:745)
    }
}
