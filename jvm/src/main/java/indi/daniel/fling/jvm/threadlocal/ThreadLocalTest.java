package indi.daniel.fling.jvm.threadlocal;

public class ThreadLocalTest {
    public static void main (String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("123");
        //ThreadLocalMap储存ThreadLocal的弱引用，所以不会影响正常GC
        //1. 清理 WeakReference， 在set， get中会清理
        //2. 普通线程在线程结束后会执行 threadLocals=null;
        //3. 不过在线程池中静态变量会导致内存泄漏，所以记得remove
        //详情见 http://ifeve.com/%E4%BD%BF%E7%94%A8threadlocal%E4%B8%8D%E5%BD%93%E5%8F%AF%E8%83%BD%E4%BC%9A%E5%AF%BC%E8%87%B4%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2/
        threadLocal.remove();
    }
}
