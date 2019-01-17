package indi.daniel.fling.springboot.aop.spring;

import indi.daniel.fling.springboot.aop.model.Animal;

/**
 * Created by daniel on 2018/12/18.
 */
public interface AnimalValidator {
    boolean isValid(Animal animal);
}
