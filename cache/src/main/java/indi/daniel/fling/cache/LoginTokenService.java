package indi.daniel.fling.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginTokenService {
    @Cacheable (value = "LOGIN_TOKEN_", key = "#userId")
    public String getLoginToken(Long userId) {
        log.info("retrieve from service");
        if (userId == 1L) {
            return "I'm 1L's token";
        } else {
            return "I'm others' token";
        }
    }
}
