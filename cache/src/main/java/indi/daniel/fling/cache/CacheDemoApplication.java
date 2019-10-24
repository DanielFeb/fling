package indi.daniel.fling.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@EnableCaching
public class CacheDemoApplication implements CommandLineRunner {
    @Autowired
    private LoginTokenService loginTokenService;

    public static void main(String[] args) {
        SpringApplication.run(CacheDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Get token of user 1!");
        System.out.println("Got token: " + loginTokenService.getLoginToken(1L));
        System.out.println("Get token of user 1 !");
        System.out.println("Got token: " + loginTokenService.getLoginToken(1L));
        System.out.println("Get token of user 2 !");
        System.out.println("Got token: " + loginTokenService.getLoginToken(2L));
    }
}
