package dev.makos.quest.mapper;

import dev.makos.quest.dto.LevelDto;
import dev.makos.quest.entity.Level;

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
