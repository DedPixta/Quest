package dev.makos.game.tombofmagic.service;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.GameDto;
import dev.makos.game.tombofmagic.entity.Game;
import dev.makos.game.tombofmagic.mapper.GameMapper;
import dev.makos.game.tombofmagic.mapper.Mapper;
import dev.makos.game.tombofmagic.repository.impl.GameRepository;

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
