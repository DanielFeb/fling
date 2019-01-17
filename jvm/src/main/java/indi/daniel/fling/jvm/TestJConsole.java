package indi.daniel.fling.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2018/11/12.
 */
public class TestJConsole {
// Using jconsole to monitor the jvm
    public byte[] tmp128k = new byte[128 * 1024];
    public TestJConsole() {
//        byte[] tmp128k = new byte[128 * 1024];
    }
    public static void main (String[] args) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fill(1000);
        System.out.println("Done");
    }

    private static void fill(int i) {
        List<TestJConsole> list = new ArrayList<TestJConsole>();
        for (int j = 0; j < i; j ++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Create new obj on heap");
            list.add(new TestJConsole());
        }
    }
}
