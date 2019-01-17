package indi.daniel.fling.springboot.aop.spring;

import indi.daniel.fling.springboot.aop.model.Animal;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 2018/12/18.
 */
@Component
public class AnimalValidatorImpl implements AnimalValidator {
    @Override
    public boolean isValid(Animal animal) {
        System.out.println("Introduce new interface and service!");
        return animal.getName().length() > 2;
    }
}
