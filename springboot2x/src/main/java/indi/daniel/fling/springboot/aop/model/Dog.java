package indi.daniel.fling.springboot.aop.model;

/**
 * Created by daniel on 2018/12/16.
 */

public class Dog implements Animal {
    private String name;
    @Override
    public void sayHello() {
        System.out.println("Wang wang! I'm " + this.getName() + "!" );
//        System.out.println(100/0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
