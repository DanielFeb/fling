package indi.daniel.fling.springboot;


/**
 * Created by daniel on 2018/12/18.
 */
public class TestSugar {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Integer g = 320;
        Integer i = (g + a);
        Long h = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(h == (a + b));
        System.out.println(h.equals(a + b));

        System.out.println(f == (g + a));
        System.out.println(e == (g + a));
        System.out.println(f == i);
        System.out.println(e == i);
        System.out.println(e == f);
        System.out.println(f.equals(g + a));
    }
}
