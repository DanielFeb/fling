package indi.daniel.fling.springboot.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 2018/12/15.
 */
@Component
public class Pet {
    private Clothes clothes;

    public Clothes getClothes() {
        return clothes;
    }

    @Autowired
    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }
}
