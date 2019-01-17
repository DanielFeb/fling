/**
 * Created by daniel on 2018/11/7.
 */
public class TestAutoBoxing {
    public static void main (String[] args) {
        /* 对于–128到127（默认是127）之间的值，Integer.valueOf(int i) 返回的是缓存的Integer对象（并不是新建对象） */
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println("i1==i2:" + (i1 == i2));

        Integer i7 = 129;
        Integer i8 = 129;
        System.out.println("i7==i8:" + (i7 == i8));

        /* Integer i1 = 10; 相当于 Integer i1 = Integer.valueOf(10); */
        Integer i3 = Integer.valueOf(11);
        Integer i4 = Integer.valueOf(11);
        System.out.println("i3==i4:" + (i3.intValue() == i4.intValue()));

        Integer i5 = new Integer(12);
        Integer i6 = new Integer(12);
        System.out.println("i5==i6:" + (i5 == i6) + ", value equals:" + (i5.intValue() == i6.intValue()));

        String s1 = "abc";
        String s2 = "abc";
        System.out.println("s1==s2:" + (s1 == s2) + ", values equals:" + (s1.equals(s2)) );

        String s3 = String.valueOf("abc");
        String s4 = String.valueOf("abc");
        System.out.println("s3==s4:" + (s3 == s4) + ", values equals:" + (s3.equals(s4)) );

        String s5 = new String("abc");
        String s6 = new String("abc");
        System.out.println("s5==s6:" + (s5 == s6) + ", values equals:" + (s5.equals(s6)) );
    }
}
