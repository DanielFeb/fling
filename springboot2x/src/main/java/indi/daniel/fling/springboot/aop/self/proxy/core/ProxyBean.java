package indi.daniel.fling.springboot.aop.self.proxy.core;

import java.lang.reflect.*;

/**
 * Created by daniel on 2018/12/16.
 */
public class ProxyBean implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;

    public static Object getProxyBean(Object obj, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = obj;
        proxyBean.interceptor = interceptor;

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), proxyBean);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(args, method, target);
        Throwable t = null;

        if(method.getName().equals("toString") ) {
            System.out.println("I'm in debug mode");
        }
        interceptor.before();
        Object retObj = null;
        try {
            if(interceptor.useAround()) {
                retObj = interceptor.around(invocation);
            } else {
                retObj = invocation.proceed();
            }
        } catch (Throwable tr) {
            t = tr;
        }
        interceptor.after();
        if(t != null) {
            interceptor.afterThrowing(t);
        } else {
            interceptor.afterReturning();
        }
        return retObj;
    }
}
