package dev.makos.game.tombofmagic.service;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.UserDto;
import dev.makos.game.tombofmagic.entity.Game;
import dev.makos.game.tombofmagic.entity.GameSession;
import dev.makos.game.tombofmagic.entity.Role;
import dev.makos.game.tombofmagic.entity.User;
import dev.makos.game.tombofmagic.mapper.Mapper;
import dev.makos.game.tombofmagic.mapper.UserMapper;
import dev.makos.game.tombofmagic.repository.impl.UserRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public class UserService {

    private final UserRepository userRepository = Container.getInstance(UserRepository.class);
    private final Mapper<UserDto, User> userMapper = new UserMapper();

    public Collection<UserDto> getAll(int pageNumber, int pageSize) {
        userRepository.startTransaction();
        try {
            return userRepository.getAll()
                    .sorted(Comparator.comparing(User::getLogin))
                    .skip((long) pageNumber * pageSize)
                    .limit(pageSize)
                    .map(userMapper::toDto)
                    .toList();
        } finally {
            userRepository.endTransaction();
        }
    }

    public Integer getAllCount() {
        return Math.toIntExact(userRepository.getAll().count());
    }

    public Optional<UserDto> findByCredentials(String login, String password) {
        userRepository.startTransaction();
        try {
            return userRepository.find(User.builder()
                            .login(login)
                            .password(password)
                            .build())
                    .findFirst()
                    .map(userMapper::toDto);
        } finally {
            userRepository.endTransaction();
        }
    }

    public Optional<UserDto> findByLogin(String login) {
        userRepository.startTransaction();
        try {
            return userRepository.find(User.builder()
                            .login(login)
                            .build())
                    .findFirst()
                    .map(userMapper::toDto);
        } finally {
            userRepository.endTransaction();
        }
    }

    public Optional<UserDto> findById(long id) {
        userRepository.startTransaction();
        try {
            return Optional.of(userRepository.getById(id))
                    .map(userMapper::toDto);
        } finally {
            userRepository.endTransaction();
        }
    }

    public boolean validateLogin(String login) {
        String regex = "^[A-Za-z\\d]{1,20}$";
        return check(regex, login);
    }

    public boolean validatePassword(String password) {
        String regex = "^[A-Za-z\\d]{1,8}$";
        return check(regex, password);
    }

    public void createUser(String login, String password, Role role) {
        userRepository.create(User.builder()
                .login(login)
                .password(password)
                .role(role)
                .build());
    }

    public void update(Long id, String login, String password, Role role) {
        User user = userRepository.getById(id);

        if (nonNull(login) && !login.isBlank()) {
            user.setLogin(login);
        }

        if (nonNull(password) && !password.isBlank()) {
            user.setPassword(password);
        }

        user.setRole(role);

        userRepository.update(user);
    }

    public void update(Long id, GameSession session) {
        userRepository.startTransaction();
        try {
            User user = userRepository.getByIdWithSession(id);

            List<Game> gamesIdWithSession = user.getGamesWithSession();
            gamesIdWithSession.add(session.getGame());

            userRepository.update(user);
        } finally {
            userRepository.endTransaction();
        }

    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    private boolean check(String regex, String input) {
        if (input == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public Role getRole(String parameter) {
        Role userRole;
        try {
            userRole = Role.valueOf(parameter);
        } catch (IllegalArgumentException e) {
            userRole = Role.GUEST;
        }
        return userRole;
    }
}
