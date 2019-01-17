package indi.daniel.fling.hibernate;

import indi.daniel.fling.hibernate.entityxml.Category;
import indi.daniel.fling.hibernate.entityxml.HibernateUtils;
import indi.daniel.fling.hibernate.entityxml.Product;
import indi.daniel.fling.hibernate.entityxml.User;
import org.hibernate.Session;

/**
 * Created by daniel on 2018/8/5.
 */
public class TestHibernate {
    public static void main (String[] args) {
        try {
            Class.forName("indi.daniel.fling.log4j.EnableLog4j");
            testXml();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testXml() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        setProductCategory(session);

        session.getTransaction().commit();
        session.close();
        HibernateUtils.shutdown();
        return;
    }

    public static void createUser(Session session) {
        User user = new User();
        user.setUsername("daniel3");
        user.setPassword("test3");
        session.save(user);
    }

    public static void setProductCategory(Session session) {
        Product product = session.get(Product.class, 1);
        Category category = session.get(Category.class, 2);
        System.out.println(product.getCategory().getName());
        product.setCategory(category);
        product.setName("newName");
        System.out.println(product.getCategory().getName());
        session.update(product);

    }

    public static void createProduct(Session session) {
        Product product = new Product();
        product.setName("product1");
        product.setPrice(100.0f);
        session.save(product);
    }

    public static void createCategory(Session session) {
        Category category = new Category();
        category.setName("category1");
        session.save(category);
    }
}
