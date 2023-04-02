package dev.makos.quest.repository.impl;

import dev.makos.quest.entity.GameSession;
import dev.makos.quest.repository.BaseRepository;
import dev.makos.quest.repository.SessionCreator;

public class GameSessionRepository extends BaseRepository<GameSession> {

    public GameSessionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, GameSession.class);
    }
}
