package dev.makos.game.tombofmagic.controller.game;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.GameSessionDto;
import dev.makos.game.tombofmagic.dto.LevelDto;
import dev.makos.game.tombofmagic.entity.GameStatus;
import dev.makos.game.tombofmagic.entity.Requirement;
import dev.makos.game.tombofmagic.service.GameService;
import dev.makos.game.tombofmagic.service.LevelService;
import dev.makos.game.tombofmagic.utils.Attribute;
import dev.makos.game.tombofmagic.utils.Go;
import dev.makos.game.tombofmagic.utils.Jsp;
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
