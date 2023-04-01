package dev.makos.game.tombofmagic.repository.impl;

import dev.makos.game.tombofmagic.entity.Level;
import dev.makos.game.tombofmagic.repository.BaseRepository;
import dev.makos.game.tombofmagic.repository.SessionCreator;

public class LevelRepository extends BaseRepository<Level> {

    public LevelRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Level.class);
    }
}
