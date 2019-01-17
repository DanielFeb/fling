package indi.daniel.fling.springboot.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by daniel on 2018/12/15.
 */
@Component
public class Clothes {
    @Value("${clothes.color}")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
