package indi.daniel.fling.springboot.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by daniel on 2018/12/15.
 */
// @Component works well here too.
//@Configuration
// scan current package as default
// lazyInit, bean instantiation and autowired will delayed to when it is used like "getBean()"
// Only affects on the scanned components, but not the beans in this AppConfig beanFactory
//@ComponentScan(lazyInit = true)
public class AppConfig {
    @Bean({"sally", "limeihui", "mona"})
    public Human sally() {
        Human human = new Human();
        human.setName("sally");
        human.setLanguage("English");
        return human;
    }
}
