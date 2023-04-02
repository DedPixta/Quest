package dev.makos.quest.repository.impl;

import dev.makos.quest.entity.Game;
import dev.makos.quest.repository.BaseRepository;
import dev.makos.quest.repository.SessionCreator;

public class GameRepository extends BaseRepository<Game> {

    public GameRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Game.class);
    }
}
