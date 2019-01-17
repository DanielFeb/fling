package indi.daniel.fling.springboot.datasource.mybatis.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//uncomment the next line to enable the configuration
//@Configuration
public class DaoBeanFactory {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Bean
    public MapperFactoryBean<UserDao> initUserDao() {
        MapperFactoryBean<UserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(UserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return bean;
    }
}
