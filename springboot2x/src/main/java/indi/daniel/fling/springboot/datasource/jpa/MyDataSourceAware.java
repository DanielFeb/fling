package indi.daniel.fling.springboot.datasource.jpa;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by daniel on 2018/12/19.
 */
@Component
public class MyDataSourceAware implements ApplicationContextAware{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataSource dataSource =  applicationContext.getBean(DataSource.class);
        System.out.println("MyDataSourceAware:");
        System.out.println(dataSource.getClass().getName());
    }
}
