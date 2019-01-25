package indi.daniel.fling.jvm.dispatch;

public class Man extends Human {

    public void wear(Clothes clothes) {
        System.out.println("Man will put on the clothes!");
    }

    public void wear(Sweater sweater) {
        System.out.println("Man will put on the sweater!");
    }
}
