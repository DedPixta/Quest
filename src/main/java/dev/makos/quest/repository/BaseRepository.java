package dev.makos.quest.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class BaseRepository<T> implements Repository<T> {

    protected final SessionCreator sessionCreator;
    private final Class<T> aClass;

    public BaseRepository(SessionCreator sessionCreator, Class<T> aClass) {
        this.sessionCreator = sessionCreator;
        this.aClass = aClass;
    }

    @Override
    public Stream<T> getAll() {
        startTransaction();
        try {
            Session session = sessionCreator.getCurrentSession();
            Query<T> query = session.createQuery("SELECT data FROM %s AS data".formatted(aClass.getSimpleName()), aClass);
            List<T> list = query.list();
            return list.stream();
        } finally {
            endTransaction();
        }
    }


    @Override
    public T getById(long id) {
        startTransaction();
        try {
            Session session = sessionCreator.getCurrentSession();
            return session.get(aClass, id);
        } finally {
            endTransaction();
        }
    }

    @Override
    public Stream<T> find(T entity) {
        startTransaction();
        try {
            Session session = sessionCreator.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(aClass);
            Root<T> root = query.from(aClass);
            query = query.select(root);
            List<Predicate> predicates = new ArrayList<>();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (
                        !field.isAnnotationPresent(Transient.class) &&
                                !field.isAnnotationPresent(OneToOne.class) &&
//                                !field.isAnnotationPresent(ManyToOne.class) && //TODO fix this
                                !field.isAnnotationPresent(OneToMany.class) &&
                                !field.isAnnotationPresent(ManyToMany.class)
                ) {
                    field.setAccessible(true);
                    try {
                        String name = field.getName();
                        Object value = field.get(entity);
                        if (Objects.nonNull(value)) {
                            Predicate condition = criteriaBuilder.equal(root.get(name), value);
                            predicates.add(condition);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            query = query.where(predicates.toArray(Predicate[]::new));
            List<T> users = session.createQuery(query).list();
            return users.stream();
        } finally {
            endTransaction();
        }
    }

    @Override
    public void create(T entity) {
        startTransaction();
        try {
            Session session = sessionCreator.getCurrentSession();
            session.save(entity);
        } finally {
            endTransaction();
        }
    }

    @Override
    public void update(T entity) {
        startTransaction();
        try {
            Session session = sessionCreator.getCurrentSession();
            session.update(entity);
        } finally {
            endTransaction();
        }
    }

    @Override
    public void deleteById(long id) {
        startTransaction();
        try {
            sessionCreator.getCurrentSession()
                    .createQuery("DELETE FROM %s WHERE id = :ID".formatted(aClass.getSimpleName()))
                    .setParameter("ID", id)
                    .executeUpdate();
        } finally {
            endTransaction();
        }
    }

    public void endTransaction() {
        sessionCreator.endTransaction();
    }

    public void startTransaction() {
        sessionCreator.startTransaction();
    }
}
