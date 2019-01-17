package indi.daniel.fling.springboot.datasource.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my.config")
public class DefaultConfig {
    public boolean enabled = true;
}
