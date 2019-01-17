package indi.daniel.fling.springboot.aop.self.proxy;

import indi.daniel.fling.springboot.aop.model.Animal;
import indi.daniel.fling.springboot.aop.model.Dog;
import indi.daniel.fling.springboot.aop.self.proxy.core.ProxyBean;

/**
 * Created by daniel on 2018/12/16.
 */
public class ProxyTest {
    public static void main (String[] args) {
        Animal dog = (Animal) ProxyBean.getProxyBean(new Dog(), new DefaultInterceptor());
        dog.sayHello();
        dog.getName();
    }
}
