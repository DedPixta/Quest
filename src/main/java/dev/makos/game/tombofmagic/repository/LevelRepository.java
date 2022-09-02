package dev.makos.game.tombofmagic.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import dev.makos.game.tombofmagic.entity.Button;
import dev.makos.game.tombofmagic.entity.Level;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Log4j2
public class LevelRepository implements Repository {
    private static LevelRepository levelRepository;

    private final ObjectMapper mapper;
    private final Map<Integer, Level> gameMap;
    private final Map<Integer, List<Integer>> adjMap;
    private List<Level> levels;

    private LevelRepository() {
        mapper = new ObjectMapper(YAMLFactory.builder()
                .disable(YAMLGenerator.Feature.SPLIT_LINES)
                .build());
        gameMap = new HashMap<>();
        loadLevels();
        createGameMap();

        adjMap = createAdjMap();
        System.out.println(adjMapToString());

        log.info(adjMapToString());
    }

    private String adjMapToString() {
        StringBuilder result = new StringBuilder();
        adjMap.forEach((key, values) -> {
            result.append(key).append(" -> ");
            for (int i = 0; i < values.size(); i++) {
                result.append(values.get(i));
                if (i != values.size() - 1){
                    result.append(" - ");
                }
            }
            result.append("\n");
        });
        return result.toString();
    }

    private void loadLevels() {
//        URL url = getClass().getClassLoader().getResource("LevelMap.yaml"); //TODO Delete
        String path = "src/main/resources/LevelMap.yaml";
        try {
            levels = mapper.readerForListOf(Level.class).readValue(new File(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGameMap() {
        levels.forEach(level -> gameMap.put(level.getId(), level));
    }

    // Log only
    private Map<Integer, List<Integer>> createAdjMap() {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (Map.Entry<Integer, Level> entry : gameMap.entrySet()) {
            List<Integer> edges = new ArrayList<>();
            Level currentLevel = entry.getValue();
            for (Button button : currentLevel.getButtons()) {
                int mainLevel = button.getMainLevel();
                if (!edges.contains(mainLevel)) {
                    edges.add(mainLevel);
                }
                int altLevel = button.getAltLevel();
                if (!edges.contains(altLevel)) {
                    edges.add(altLevel);
                }

                Collections.sort(edges);
                adjMap.put(entry.getKey(), edges);
            }
        }
        return adjMap;
    }

    public static LevelRepository getInstance(){
        if(levelRepository == null) {
            levelRepository = new LevelRepository();
        }
        return levelRepository;
    }

    @Override
    public Map<Integer, Level> getGameMap() {
        return gameMap;
    }

}
