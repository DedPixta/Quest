package dev.makos.quest.repository.impl;

import dev.makos.quest.entity.User;
import dev.makos.quest.repository.BaseRepository;
import dev.makos.quest.repository.SessionCreator;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class UserRepository extends BaseRepository<User> {

    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, User.class);
    }

    public User getByIdWithSession(long id) {
        startTransaction();
        try {
            Session session = sessionCreator.getCurrentSession();
            User user = session.get(User.class, id);
            Hibernate.initialize(user);
            return user;
        } finally {
            endTransaction();
        }
    }
}
