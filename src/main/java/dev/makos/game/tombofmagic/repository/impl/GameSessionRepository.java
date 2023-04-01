package dev.makos.game.tombofmagic.repository.impl;

import dev.makos.game.tombofmagic.entity.GameSession;
import dev.makos.game.tombofmagic.repository.BaseRepository;
import dev.makos.game.tombofmagic.repository.SessionCreator;

public class GameSessionRepository extends BaseRepository<GameSession> {

    public GameSessionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, GameSession.class);
    }
}
