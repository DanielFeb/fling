package indi.daniel.fling.springboot.aop.self.proxy;

import indi.daniel.fling.springboot.aop.self.proxy.core.Interceptor;
import indi.daniel.fling.springboot.aop.self.proxy.core.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by daniel on 2018/12/16.
 */
public class DefaultInterceptor implements Interceptor {

    @Override
    public void before() {
        System.out.println("1. Before ...");
    }

    @Override
    public void after() {
        System.out.println("4. After ...");
    }

    @Override
    public boolean useAround() {
        return true;
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        Object retObj;
        System.out.println("2. around before ...");
        retObj = invocation.proceed();
        System.out.println("3. around after ...");
        return retObj;
    }

    @Override
    public void afterReturning() {
        System.out.println("5. Returning ...");
    }

    @Override
    public void afterThrowing(Throwable t) throws Throwable {
        System.out.println("5. Throwing ...");
    }
}
