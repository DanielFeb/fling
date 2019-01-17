package indi.daniel.fling.springboot.datasource.mybatis;

import indi.daniel.fling.springboot.datasource.mybatis.config.DefaultConfig;
import indi.daniel.fling.springboot.datasource.mybatis.config.YamlPropertyLoaderFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * Created by daniel on 2018/12/23.
 */

@PropertySource(value = "classpath:datasource-mybatis.yml", factory = YamlPropertyLoaderFactory.class)
@EnableConfigurationProperties(DefaultConfig.class)
@SpringBootApplication(scanBasePackages = "indi.daniel.fling.springboot.datasource.mybatis")
//@Mapper Annotation will auto scanned by spring
@MapperScan(
        value = "indi.daniel.fling.springboot.datasource.mybatis.dao",
        //限定接口必须包含注解 @Repository
        annotationClass = Repository.class
    )
public class MyBatisTest{

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .listeners(new EnvironmentPrepareListener())
                .sources(MyBatisTest.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .build()
                .run(args);
    }

    static class EnvironmentPrepareListener implements
            ApplicationListener<ApplicationEnvironmentPreparedEvent> {

        @Override
        public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
            // 设置动态加载文件要使用的active.profile系统变量
            ConfigurableEnvironment env = event.getEnvironment();
            if(env != null){
                System.out.println("start eds Application , active profiles : "
                        + Arrays.toString(env.getActiveProfiles()));
                String[] activeProfiles = env.getActiveProfiles();
                if(activeProfiles != null && activeProfiles.length > 0){
                    String active = activeProfiles[0];
                    System.setProperty("spring.profiles.active",active);
                    System.out.println("eds system property [spring.profiles.active] variable : "+active);
                }
            }
        }
    }
}
