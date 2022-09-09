package dev.makos.game.tombofmagic.service;

import dev.makos.game.tombofmagic.entity.Element;
import dev.makos.game.tombofmagic.entity.Level;
import dev.makos.game.tombofmagic.repository.LevelRepository;
import dev.makos.game.tombofmagic.repository.Repository;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public enum GameService {

    INSTANCE;

    private final Repository gameRepository = LevelRepository.getInstance();

    public Level getNextLevel(Element element, int levelId, int clickedButton) {
        log.info("Request: Level_{}_{} Element - {}", levelId, clickedButton, element);

        Map<Integer, Level> gameMap = gameRepository.getGameMap();
        Level currentLevel = gameMap.get(levelId);
        int nextLevelId = currentLevel.getNextLevelId(element, clickedButton);

        log.info("Response: Next level - {}", nextLevelId);

        return gameMap.get(nextLevelId);
    }

    public Level getLevel(int levelId){
        log.info("Request: Level_{}", levelId);

        Map<Integer, Level> gameMap = gameRepository.getGameMap();
        Level level = gameMap.get(levelId);

        log.info("Response: Level_{}", level.getId());

        return level;
    }
}
