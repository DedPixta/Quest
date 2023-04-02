package dev.makos.quest.service;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.GameDto;
import dev.makos.quest.entity.Game;
import dev.makos.quest.mapper.GameMapper;
import dev.makos.quest.mapper.Mapper;
import dev.makos.quest.repository.impl.GameRepository;

import java.util.Collection;

public class PlayService {

    private final GameRepository gameRepository = Container.getInstance(GameRepository.class);
    private final Mapper<GameDto, Game> gameMapper = new GameMapper();


    public Collection<GameDto> getAll() {
        gameRepository.startTransaction();
        try {
            return gameRepository.getAll()
                    .map(gameMapper::toDto)
                    .toList();
        } finally {
            gameRepository.endTransaction();
        }
    }
}
