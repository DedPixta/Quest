package dev.makos.game.tombofmagic.repository.impl;

import dev.makos.game.tombofmagic.entity.Button;
import dev.makos.game.tombofmagic.repository.BaseRepository;
import dev.makos.game.tombofmagic.repository.SessionCreator;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Stream;

public class ButtonRepository extends BaseRepository<Button> {

    public ButtonRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Button.class);
    }

    public Stream<Button> getAllByLevelId(long levelId) {
        startTransaction();
        try {
                Session session = sessionCreator.getCurrentSession();
                Query<Button> query = session.createQuery("SELECT b FROM Button as b WHERE b.level.id = :ID", Button.class)
                                    .setParameter("ID", levelId);
                List<Button> list = query.list();
                return list.stream();
        } finally {
            endTransaction();
        }
    }
}
