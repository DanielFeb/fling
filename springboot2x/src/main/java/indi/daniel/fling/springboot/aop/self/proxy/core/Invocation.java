package indi.daniel.fling.springboot.aop.self.proxy.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by daniel on 2018/12/16.
 */
public class Invocation {
    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object[] params, Method method, Object target) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }
}
