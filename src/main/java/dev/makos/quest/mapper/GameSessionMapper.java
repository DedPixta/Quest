package dev.makos.quest.mapper;

import dev.makos.quest.dto.GameSessionDto;
import dev.makos.quest.entity.GameSession;

public class GameSessionMapper implements Mapper<GameSessionDto, GameSession> {

    @Override
    public GameSessionDto toDto(GameSession entity) {
        return GameSessionDto.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .gameId(entity.getGame().getId())
                .currentQuestionId(entity.getCurrentLevel().getId())
                .requirement(entity.getRequirement())
                .gameStatus(entity.getGameStatus())
                .build();
    }
}
