package dev.makos.quest.mapper;

import dev.makos.quest.dto.GameDto;
import dev.makos.quest.entity.Game;

public class GameMapper implements Mapper<GameDto, Game> {

    @Override
    public GameDto toDto(Game entity) {
        return GameDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .authorId(entity.getAuthor() != null ? entity.getAuthor().getId(): null)
                .startLevelId(entity.getStartLevel().getId())
                .requirementDescription(entity.getRequirementDescription())
                .requirements(entity.getRequirements())
                .build();
    }
}
