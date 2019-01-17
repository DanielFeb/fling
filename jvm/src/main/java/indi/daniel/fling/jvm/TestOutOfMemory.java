package indi.daniel.fling.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2018/11/11.
 */
public class TestOutOfMemory {
    public static void main (String[] args){
        List<TestOutOfMemory> demoList = new ArrayList<TestOutOfMemory>();
        /* VMOptions: -XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m */
        /* -XX:+HeapDumpOnOutOfMemoryError : generate .hprof file, use "eclipse memory analyzer" */
        /* java.lang.OutOfMemoryError: Java heap space */
        while(true) {
            demoList.add(new TestOutOfMemory());
        }
    }
}
