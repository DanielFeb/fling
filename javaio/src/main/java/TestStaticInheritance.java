/**
 * Created by daniel on 2018/11/7.
 */
public class TestStaticInheritance {
    public static void main(String[] args) {
        Parent p = new Parent();
        Parent c = new Child();
        Fruit f = new Fruit();
        Fruit a = new Apple();
        p.eat(f);
        c.eat(a);
    }
}

class Parent {
    public /*static*/ void eat(Fruit f) {
        System.out.println("Parent eat fruit: " + f.getName());
    }

    public /*static*/ void eat(Apple a) {
        System.out.println("Parent eat apple: " + a.getName());
    }
}

class Child extends Parent {
    public /*static*/ void eat(Fruit f) {
        System.out.println("Child eat fruit: " + f.getName());
    }

    public /*static*/ void eat(Apple a) {
        System.out.println("Child eat apple: " + a.getName());
    }
}

class Fruit {
    public String getName () {
        return "fruit";
    }
}


class Apple extends Fruit {
    public String getName () {
        return "apple";
    }

}
