package indi.daniel.fling.springboot.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by daniel on 2018/12/15.
 */
@Component()
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ConfigurationProperties("human")
public class Human
    // all these interfaces are not suggested, api intrusion
    // @PostConstruct @PreDestroy BeanPostProcessor are suggested
        implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;
    private String language;
    private Clothes clothes;

    public Clothes getClothes() {
        return clothes;
    }

    @Autowired
    public void setClothes(Clothes clothes) {
        System.out.println("0. Human, populate bean Lazy autowired clothes!");
        this.clothes = clothes;
    }

    public String getName() {
        return name;
    }
// setter got higher priority than attributes
//    @Value("Sally")
    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void sayHello() {

        System.out.println("8. Human, I'm living, my name is " + name + "! And i'm in " + clothes.getColor() + '!');
    }

    @Override
    public boolean equals(Object obj) {
        if (!( obj instanceof  Human)) {
            return false;
        }
        Human tmp = (Human) obj;
        return this.name.equals(tmp.name) && this.language.equals(tmp.language) ;
    }

    //1. BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("1. Human BeanNameAware setBeanName: " + name + ", " + this.getClass().getSimpleName());
    }
    //2. BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("2. Human BeanFactoryAware setBeanFactory " );
    }
    //3. ApplicationContext, when ioc container is an ApplicationContext
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("3. Human ApplicationContextAware setApplicationContext ");
    }
    //4. BeanPostProcessor.postProcessBeforeInitialization
    //5. After constructor, suggested @PostConstruct, no api intrusion
    @PostConstruct
    public void helloWorld() {
        System.out.println("5. Human, suggested @PostConstruct, suggested, i'm born just now!");
    }

    //6.InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6. Human InitializingBean afterPropertiesSet ");

    }
    //7. BeanPostProcessor.postProcessAfterInitialization
    //8. live for a while
    //9. Before destroy, @PreDestroy, no api intrusion
    @PreDestroy
    public void byeBye() {
        System.out.println("9. Human, suggested @PreDestroy it's time to say goodbye!");
    }

    //10. DisposableBean
    @Override
    public void destroy() throws Exception {

        System.out.println("10. Human DisposableBean destroy! " );
    }
}
