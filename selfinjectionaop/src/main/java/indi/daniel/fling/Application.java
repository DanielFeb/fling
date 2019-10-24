package indi.daniel.fling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableCaching
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //自定义的bean上的aop注解同样会生效
    @Bean
    public Increment get() {
        return new Increment();
    }

    @Autowired
    private Increment increment;


    public void run(String... args) throws Exception {
        System.out.println(increment.getValue());
        System.out.println(increment.getValue());
        System.out.println(increment.getValue());
    }
}
