package indi.daniel.fling.springboot.aop.spring;

import indi.daniel.fling.springboot.aop.model.Animal;
import indi.daniel.fling.springboot.aop.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daniel on 2018/12/18.
 */
@Controller
@RequestMapping("/animal")
@SpringBootApplication(scanBasePackages = {"indi.daniel.fling.springboot.aop"})
public class AspectTest {
    @Autowired
    private AnimalService animalService;

    @ResponseBody
    @RequestMapping("/adopt")
    public Animal getAnimal (String name) {
        Animal animal = new Dog();
        animal.setName(name);
        animalService.adopt(animal);
        return animal;
    }

    public static void main (String[] args) {
        SpringApplication.run(AspectTest.class);
    }

}
