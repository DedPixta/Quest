package dev.makos.quest.service;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.GameDto;
import dev.makos.quest.dto.GameSessionDto;
import dev.makos.quest.dto.UserDto;
import dev.makos.quest.entity.*;
import dev.makos.quest.error.AppError;
import dev.makos.quest.mapper.GameMapper;
import dev.makos.quest.mapper.GameSessionMapper;
import dev.makos.quest.mapper.Mapper;
import dev.makos.quest.repository.impl.GameRepository;
import dev.makos.quest.repository.impl.GameSessionRepository;
import dev.makos.quest.repository.impl.LevelRepository;
import dev.makos.quest.repository.impl.UserRepository;

import java.util.Objects;
import java.util.Optional;

public class GameService {

    private final GameRepository gameRepository = Container.getInstance(GameRepository.class);
    private final GameSessionRepository gameSessionRepository = Container.getInstance(GameSessionRepository.class);
    private final LevelRepository levelRepository = Container.getInstance(LevelRepository.class);
    private final UserRepository userRepository = Container.getInstance(UserRepository.class);
    private final Mapper<GameSessionDto, GameSession> gameSessionMapper = new GameSessionMapper();
    private final Mapper<GameDto, Game> gameMapper = new GameMapper();

    public GameDto getGame(Long id) {
        return gameMapper.toDto(gameRepository.getById(id));
    }

    public GameSessionDto getSession(UserDto userDto, Long gameId) {
        gameRepository.startTransaction();
        try {
            Game game = gameRepository.getById(gameId);
            GameSessionDto sessionDto;
            if (userDto == null) {
                sessionDto = gameSessionMapper.toDto(GameSession.builder()
                        .game(game)
                        .currentLevel(game.getStartLevel())
                        .build());
                return sessionDto;
            }

            User userFromDB = userRepository.getById(userDto.getId());
            Optional<Game> gameWithSession = userFromDB.getGamesWithSession()
                    .stream()
                    .filter(gameInList -> Objects.equals(gameInList.getId(), gameId))
                    .findFirst();

            if (gameWithSession.isPresent()) {
                GameSession findSession = GameSession.builder()
                        .game(game)
                        .user(userFromDB)
                        .build();
                sessionDto = gameSessionRepository.find(findSession)
                        .findFirst()
                        .map(gameSessionMapper::toDto)
                        .orElseThrow(() -> new AppError("Session not found for gameID - " + gameId + " userID - " + userDto.getId()));
                // TODO replace for display error with redirect
            } else {
                GameSession userSession = GameSession.builder()
                        .user(userFromDB)
                        .game(game)
                        .currentLevel(game.getStartLevel())
                        .gameStatus(GameStatus.LOST)
                        .requirement(Requirement.builder().id(1L).build())
                        .build();

                gameSessionRepository.create(userSession);
                sessionDto = gameSessionRepository.find(userSession)
                        .findFirst()
                        .map(gameSessionMapper::toDto)
                        .orElseThrow(() -> new AppError("Session not found for gameID - " + gameId + " userID - " + userDto.getId()));
            }
            return sessionDto;
        } finally {
            gameRepository.endTransaction();
        }
    }

    public void updateRequirement(GameSessionDto gameSessionDto, Requirement requirement) {
        gameSessionDto.setRequirement(requirement);
        Long id = gameSessionDto.getId();
        if (id != null) {
            GameSession gameSession = gameSessionRepository.getById(id);
            gameSession.setRequirement(requirement);
            gameSessionRepository.update(gameSession);
        }
    }

    public void updateStatus(GameSessionDto gameSessionDto, GameStatus status) {
        gameSessionDto.setGameStatus(status);
        Long id = gameSessionDto.getId();
        if (id != null) {
            GameSession gameSession = gameSessionRepository.getById(gameSessionDto.getId());
            gameSession.setGameStatus(status);
            gameSessionRepository.update(gameSession);
        }
    }

    public void updateLevel(GameSessionDto gameSessionDto, Long nextLevelId) {
        gameSessionDto.setCurrentQuestionId(nextLevelId);
        Long id = gameSessionDto.getId();
        if (id != null) {
            GameSession gameSession = gameSessionRepository.getById(gameSessionDto.getId());
            Level nextLevel = levelRepository.getById(nextLevelId);
            gameSession.setCurrentLevel(nextLevel);
            gameSessionRepository.update(gameSession);
        }
    }
}
