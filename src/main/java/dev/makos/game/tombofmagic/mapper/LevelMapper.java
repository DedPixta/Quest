package dev.makos.game.tombofmagic.mapper;

import dev.makos.game.tombofmagic.dto.LevelDto;
import dev.makos.game.tombofmagic.entity.Level;

public class LevelMapper implements Mapper<LevelDto, Level> {

    @Override
    public LevelDto toDto(Level entity) {
        return LevelDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .description(entity.getDescription())
                .gameStatus(entity.getGameStatus())
                .build();
    }
}
