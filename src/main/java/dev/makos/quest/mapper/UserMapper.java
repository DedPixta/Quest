package dev.makos.quest.mapper;

import dev.makos.quest.dto.UserDto;
import dev.makos.quest.entity.Game;
import dev.makos.quest.entity.User;

public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .role(entity.getRole())
                .gamesIdWithSession(entity.getGamesWithSession()
                        .stream()
                        .map(Game::getId)
                        .toList())
                .build();
    }
}
