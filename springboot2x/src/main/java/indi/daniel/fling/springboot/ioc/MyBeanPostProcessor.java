package indi.daniel.fling.springboot.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.*;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 2018/12/15.
 */
@Component
public class MyBeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Human) {
            System.out.println("4. Human, Suggested feature, BeanPostProcessor postProcessBeforeInitialization: " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Human) {
            System.out.println("7. Human, Suggested feature, BeanPostProcessor postProcessAfterInitialization: " + beanName);
        }
        return bean;
    }
}
