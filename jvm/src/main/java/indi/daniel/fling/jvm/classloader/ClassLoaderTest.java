package indi.daniel.fling.jvm.classloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;

/**
 * Created by daniel on 2018/12/5.
 */
public class ClassLoaderTest extends EmptyParent {
    public static void main (String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            // we usually only need override findClass in normal cases
            // we don't need to override loadClass
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };
        Class cls =  classLoader.loadClass("indi.daniel.fling.jvm.classloader.ClassLoaderTest");
        Class parentCls = cls.getSuperclass();
        Object object = cls.newInstance();

        System.out.println(cls);
        System.out.println(cls.getClassLoader());
        System.out.println(cls.getClassLoader().getParent());

        System.out.println(parentCls);
        System.out.println(parentCls.getClassLoader());
        System.out.println(parentCls.getClassLoader().getParent());
        System.out.println(indi.daniel.fling.jvm.classloader.ClassLoaderTest.class);
        System.out.println(indi.daniel.fling.jvm.classloader.ClassLoaderTest.class.getClassLoader());
        System.out.println(indi.daniel.fling.jvm.classloader.ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(object instanceof indi.daniel.fling.jvm.classloader.ClassLoaderTest);
    }
}

class EmptyParent {

}
