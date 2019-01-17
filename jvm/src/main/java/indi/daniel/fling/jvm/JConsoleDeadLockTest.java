package indi.daniel.fling.jvm;

/**
 * Created by daniel on 2018/11/27.
 */
public class JConsoleDeadLockTest {

    static class SynAddRunnable implements Runnable {
        int a, b;

        public SynAddRunnable (int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }

        }
    }

    public static void main(String args[]) {
        new Thread(new SynAddRunnable(1,2), "AddThreadA").start();
        new Thread(new SynAddRunnable(2,1),"AddThreadB").start();
    }

}

