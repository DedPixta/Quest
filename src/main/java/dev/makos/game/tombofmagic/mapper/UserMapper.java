package dev.makos.game.tombofmagic.mapper;

import dev.makos.game.tombofmagic.dto.UserDto;
import dev.makos.game.tombofmagic.entity.Game;
import dev.makos.game.tombofmagic.entity.User;

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
