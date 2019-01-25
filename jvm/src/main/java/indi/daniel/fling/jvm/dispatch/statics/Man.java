package indi.daniel.fling.jvm.dispatch.statics;

public class Man extends Human {

    public void wear(Clothes clothes) {
        System.out.print("Man will put on the clothes!");
    }

    public void wear(Sweater sweater) {
        System.out.print("Man will put on the sweater!");
    }
}
