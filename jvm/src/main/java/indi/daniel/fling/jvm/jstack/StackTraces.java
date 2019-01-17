package indi.daniel.fling.jvm.jstack;

import java.util.Map;

/**
 * Created by daniel on 2018/11/26.
 */
public class StackTraces {
    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet() ){
            Thread thread = (Thread) entry.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) entry.getValue();
            System.out.println("THREAD-" + thread.getName() + ":");
            for (StackTraceElement element: stack) {
                System.out.println("\t" + element);
            }
        }
    }
}
