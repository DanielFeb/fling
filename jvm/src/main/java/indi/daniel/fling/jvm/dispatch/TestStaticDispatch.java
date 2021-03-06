package indi.daniel.fling.jvm.dispatch;

public class TestStaticDispatch {
    public static void main (String[] args) {
        System.out.println("==================静态分派====OO的重载===========================================");
        Man man = new Man();
        Human human = (Human) man;
        Sweater sweater  = new Sweater();
        Clothes clothes  = (Clothes) sweater;

        System.out.println("对象的动态类型都是 Man");
        System.out.println("参数的动态类型都是 Sweater");
        System.out.println();

        System.out.println("当参数的静态类型分别为 Sweater 和 Clothes 时，对象静态类型都是Man，结果却不同:");
        man.wear(clothes); // Man will put on the clothes!
        man.wear(sweater); // Man will put on the sweater!

        System.out.println();

        System.out.println("当对象的静态类型分别为 Man 和 Human 时，参数类型都是Sweater，结果却不同:");
        man.wear(sweater); // Man will put on the sweater!
        human.wear(sweater); // Man will put on the clothes!
        System.out.println();

        System.out.println("根本原因是class文件中方法的符号引用是用静态类型组成的！详情见class字节码文件： javap -v TestStaticDispatch");
        System.out.println("静态分派由参数及调用对象的静态类型两个宗量决定, 故java是个静态多分派的语言！！！");

        System.out.println();
        System.out.println("==================动态分派====OO的重写===========================================");

        System.out.printf("两个对象静态类型都是Human，但是实际类型分别为Human和Man。");
        Human realHuman = new Human();
        Human fakeHuman = new Man();
        Clothes realClothes  = new Clothes();
        System.out.println("传入相同参数，从静态分配可知符号引用相同(indi/daniel/fling/jvm/dispatch/Human.wear:(Lindi/daniel/fling/jvm/dispatch/Clothes;)V)，但是结果不同");
        realHuman.wear(realClothes); // Human will put on the clothes!
        fakeHuman.wear(realClothes); // Man will put on the clothes!

        //TODO: 1.7之前动态单分派， 之后动态多分派？
    }
}
