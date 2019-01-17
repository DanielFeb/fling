package indi.daniel.fling.springboot.aop.spring;

import indi.daniel.fling.springboot.aop.model.Animal;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 2018/12/18.
 */
@Service
public class AnimalServiceImpl implements AnimalService{
    public void adopt(Animal animal) {
        animal.sayHello();
        System.out.println(animal.getName() + " is adopted!" );
    }
}
