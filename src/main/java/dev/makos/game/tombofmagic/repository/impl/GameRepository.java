package dev.makos.game.tombofmagic.repository.impl;

import dev.makos.game.tombofmagic.entity.Game;
import dev.makos.game.tombofmagic.repository.BaseRepository;
import dev.makos.game.tombofmagic.repository.SessionCreator;

public class GameRepository extends BaseRepository<Game> {

    public GameRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Game.class);
    }
}
