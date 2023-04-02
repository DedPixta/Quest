package dev.makos.quest.controller.game;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.GameSessionDto;
import dev.makos.quest.dto.LevelDto;
import dev.makos.quest.entity.GameStatus;
import dev.makos.quest.entity.Requirement;
import dev.makos.quest.service.GameService;
import dev.makos.quest.service.LevelService;
import dev.makos.quest.utils.Attribute;
import dev.makos.quest.utils.Go;
import dev.makos.quest.utils.Jsp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LevelServlet", value = Go.LEVEL)
public class LevelServlet extends HttpServlet {

    private final LevelService levelService = Container.getInstance(LevelService.class);
    private final GameService gameService = Container.getInstance(GameService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();

        GameSessionDto gameSessionDto = (GameSessionDto) currentSession.getAttribute(Attribute.GAME_SESSION);

        String button = req.getParameter(Attribute.BUTTON);
        Requirement requirement = gameSessionDto.getRequirement();
        Long currentLevelId = gameSessionDto.getCurrentQuestionId();
        LevelDto nextLevel = levelService.getLevel(currentLevelId, requirement);
        if (button == null) {
            gameService.updateStatus(gameSessionDto, GameStatus.PLAY);
        } else {
            if (GameStatus.LOST.equals(nextLevel.getGameStatus())) {
                gameService.updateStatus(gameSessionDto, GameStatus.LOST);
                req.getRequestDispatcher("game?gameId=" + gameSessionDto.getGameId()).forward(req, resp);
            } else if (GameStatus.WIN.equals(nextLevel.getGameStatus())) {
                gameService.updateStatus(gameSessionDto, GameStatus.WIN);
                req.getRequestDispatcher("game?gameId=" + gameSessionDto.getGameId()).forward(req, resp);
            } else {
                int buttonNumber = Integer.parseInt(button);
                Long nextLevelId = levelService.getNextLevelId(currentLevelId, requirement, buttonNumber);
                gameService.updateStatus(gameSessionDto, nextLevel.getGameStatus());
                nextLevel = levelService.getLevel(nextLevelId, requirement);
                gameService.updateLevel(gameSessionDto, nextLevelId);
            }
        }

        if (Objects.nonNull(nextLevel)) {
            req.setAttribute(Attribute.LEVEL, nextLevel);
            Jsp.forward(req, resp, Jsp.LEVEL);
        } else {
            req.getRequestDispatcher("game?gameId=" + gameSessionDto.getGameId()).forward(req, resp);
        }
    }
}
