package dev.makos.game.tombofmagic.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import dev.makos.game.tombofmagic.entity.Level;
import dev.makos.game.tombofmagic.service.ImageService;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class LevelRepository implements Repository {
    private static LevelRepository levelRepository;

    private final ObjectMapper mapper;
    private final Map<Integer, Level> gameMap;
    private List<Level> levels;

    private LevelRepository() {
        mapper = new ObjectMapper(YAMLFactory.builder()
                .disable(YAMLGenerator.Feature.SPLIT_LINES)
                .build());
        gameMap = new HashMap<>();
        loadLevels();
        createGameMap();
    }

    private void loadLevels() {
        String path = Objects.requireNonNull(ImageService.class.getResource("/"))
                .toString()
                .replace("file:/", "");

        String fileName = "LevelMap.yaml";

        try {
            levels = mapper.readerForListOf(Level.class).readValue(new File(path+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGameMap() {
        levels.forEach(level -> gameMap.put(level.getId(), level));
    }

    public static LevelRepository getInstance() {
        if (levelRepository == null) {
            levelRepository = new LevelRepository();
        }
        return levelRepository;
    }

    @Override
    public Map<Integer, Level> getGameMap() {
        return gameMap;
    }

}
