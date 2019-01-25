package indi.daniel.fling.jvm.dispatch.statics;

public class TestStaticDispatch {
    public static void main (String[] args) {
        Man man = new Man();
        Human human = (Human) man;
        Sweater sweater  = new Sweater();
        Clothes clothes  = (Clothes) sweater;

        System.out.println("对象的动态类型都是 Man");
        System.out.println("参数的动态类型都是 Sweater");
        System.out.println();

        System.out.println("当参数的静态类型分别为 Sweater 和 Clothes 时，对象静态类型都是Man，结果却不同:");
        man.wear(clothes);
        man.wear(sweater);

        System.out.println();

        System.out.println("当对象的静态类型分别为 Man 和 Human 时，参数类型都是Sweater，结果却不同:");
        man.wear(sweater);
        human.wear(sweater);
        System.out.println();

        System.out.println("根本原因是class文件中方法的符号引用是用静态类型组成的！详情见class字节码文件： javap -v TestStaticDispatch");
        System.out.println("故java是个静态多分派的语言！！！");
    }
}
