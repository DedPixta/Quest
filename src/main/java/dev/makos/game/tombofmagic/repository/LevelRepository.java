package dev.makos.game.tombofmagic.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import dev.makos.game.tombofmagic.entity.Button;
import dev.makos.game.tombofmagic.entity.Element;
import dev.makos.game.tombofmagic.entity.Level;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

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
                if (i != values.size() - 1) {
                    result.append(" - ");
                }
            }
            result.append("\n");
        });
        return result.toString();
    }

    private void loadLevels() {
//        URL url = getClass().getClassLoader().getResource("LevelMap.yaml"); //TODO Delete
//        String path = "src/main/resources/LevelMap.yaml";
//        String filePath = "/WEB-INF/classes/LevelMap.yaml";
//        ServletContext servletContext = getServletContext();
//        String contextPath = servletContext.getRealPath(File.separator);
//        try {
//            levels = mapper.readerForListOf(Level.class).readValue(new File(contextPath+filePath));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        levels = List.of(
                new Level(0, "Пролог", "entry.jpg", "История повествует о странствующем волшебнике, искавшего древний источник знаний. Согласно древним письменностям источником является заброшенная Гробница магии, секреты которой так и не были раскрыты. Потратив годы на поиски, он обнаружил её в непроходимых джунглях. Но сможет ли он постичь новых знаний или его ожидает опасность, будет зависеть от принятых решений…",
                        List.of(new Button("Далее", 1))),
                new Level(1, "Вход в гробницу", "entry.jpg", "Вход в гробницу находился в самой глуши леса. Он порос растительностью и трудно было разобрать надписи, высеченные на нем. Выглядело все это зловеще, даже пугающе…",
                        List.of(new Button("Войти", 3),
                                new Button("Не входить", 4),
                                new Button("Осмотреться", 2))),
                new Level(2, "Осмотр входа", "entry.jpg", "Вы осматриваетесь. Рядом стоящий монумент привлекает ваше внимание. На нем описывается некая тайная сила, неподвластной никому... Под надписью изображен знак похожий на руну магии, возможно, он означает что-то важное. Из-за множества пройденных лет руна покрылась толстым слоем пыли и копоти.",
                        List.of(new Button("Посмотреть на руну", 5),
                                new Button("Очистить с помощью магии", "Очистить с помощью магии", 7, 6, Element.WATER))),
                new Level(3, "Спуск", "entry.jpg", "Войдя в гробницу, вы начинаете спускаться по мрачной лестнице. Она введет куда-то глубоко вниз. Лишь остатки солнечного света освещают вам путь. Каждая пройденная ступенька внушает в вас долю сомнению, стоило ли сюда входить и безопасно ли идти дальше…",
                        List.of(new Button("Продолжить спускаться", 8))),
                new Level(4, "Поражение - уход", "entry.jpg", "Вы побоялись за свою жизнь и ушли, так и не узнав, что скрывает гробница.",
                        List.of(new Button("Новая игра", -1)))
        );

//        ServletContext servletContext = getServletContext();
//        String contextPath = servletContext.getRealPath(File.separator);

//        for (Level level : levels) {
//            String image = level.getImage();
//            String imagePath = String.valueOf(root.resolve(image));
//            level.setImage(imagePath);
//        }

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
