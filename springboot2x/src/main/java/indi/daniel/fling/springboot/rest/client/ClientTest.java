package indi.daniel.fling.springboot.rest.client;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * Created by daniel on 2018/12/23.
 */
@Configuration
public class ClientTest {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main (String args[]) {

    }
}
