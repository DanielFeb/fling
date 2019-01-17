package indi.daniel.fling.springboot.datasource.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by daniel on 2018/12/19.
 */
@PropertySource("classpath:datasource-jpa.properties")
@SpringBootApplication(scanBasePackages = "indi.daniel.fling.springboot.datasource.jpa")
@EnableJpaRepositories(basePackages = "indi.daniel.fling.springboot.datasource.jpa.dao")
@EntityScan(basePackages = "indi.daniel.fling.springboot.datasource.jpa.entity")
public class DataSourceTest {
    public static void main(String args[]){
        SpringApplication.run(DataSourceTest.class);
    }
}
