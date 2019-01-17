package indi.daniel.fling.jvm;

import java.util.Scanner;

/**
 * Created by daniel on 2018/11/27.
 */
public class JConsoleThreadTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();

        new Thread(() -> {
            while(true) {
            }
        }, "While true").start();

        sc.next();
        testWait(new Object());

    }

    private static void testWait(Object obj) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Wait").start();
    }
}
