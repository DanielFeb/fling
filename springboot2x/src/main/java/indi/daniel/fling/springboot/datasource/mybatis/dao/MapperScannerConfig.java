package indi.daniel.fling.springboot.datasource.mybatis.dao;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//uncomment the next line to enable the configuration
//@Configuration
public class MapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("indi.daniel.fling.springboot.datasource.mybatis");
        return mapperScannerConfigurer;
    }
}
