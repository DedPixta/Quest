package dev.makos.quest.repository.impl;

import dev.makos.quest.entity.Level;
import dev.makos.quest.repository.BaseRepository;
import dev.makos.quest.repository.SessionCreator;

public class LevelRepository extends BaseRepository<Level> {

    public LevelRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Level.class);
    }
}
