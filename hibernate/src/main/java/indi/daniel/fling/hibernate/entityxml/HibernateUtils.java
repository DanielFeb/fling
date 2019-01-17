package indi.daniel.fling.hibernate.entityxml;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by daniel on 2018/8/7.
 */

public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
