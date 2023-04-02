package dev.makos.quest.repository;

import dev.makos.quest.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicInteger;


public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;
    private final AtomicInteger counter = new AtomicInteger();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public SessionCreator() {
        Configuration configuration = new Configuration();
        sessionFactory = getSessionFactory(configuration);
    }

    private SessionFactory getSessionFactory(Configuration configuration) {
        LiquibaseChecker.updateDataBase(configuration);
        configuration.addAnnotatedClass(Button.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(GameSession.class);
        configuration.addAnnotatedClass(Level.class);
        configuration.addAnnotatedClass(Requirement.class);
        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void startTransaction() {
        if (counter.getAndIncrement() == 0) {
            Session session = getCurrentSession();
            session.beginTransaction();
        }
    }

    public void endTransaction() {
        Session session = getCurrentSession();
        if (counter.decrementAndGet() == 0) {
            try {
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        } else {
            session.flush();
        }
    }

    @Override
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
