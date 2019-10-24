package indi.daniel.fling;

import org.springframework.cache.annotation.Cacheable;

//@Component
public class Increment {
    private Integer value;


    @Cacheable("Increment")
    public Integer getValue() {
        System.out.println("value ++");
        return  ++ this.value;
    }

    public Increment() {
        this.value = 0;
    }
}
