package indi.daniel.fling.springboot.aop.self.proxy.core;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by daniel on 2018/12/16.
 */
public interface Interceptor {
    void before();
    void after();
    boolean useAround();
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
    void afterReturning();
    void afterThrowing(Throwable t) throws Throwable;
}
