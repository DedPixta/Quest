package dev.makos.game.tombofmagic.mapper;

import dev.makos.game.tombofmagic.dto.GameDto;
import dev.makos.game.tombofmagic.entity.Game;

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
