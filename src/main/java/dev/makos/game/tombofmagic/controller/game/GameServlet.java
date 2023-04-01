package dev.makos.game.tombofmagic.controller.game;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.GameDto;
import dev.makos.game.tombofmagic.dto.GameSessionDto;
import dev.makos.game.tombofmagic.dto.UserDto;
import dev.makos.game.tombofmagic.error.AppError;
import dev.makos.game.tombofmagic.service.GameService;
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

@WebServlet(name = "GameServlet", value = Go.GAME)
public class GameServlet extends HttpServlet {

    private final GameService gameService = Container.getInstance(GameService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        UserDto user = (UserDto) currentSession.getAttribute(Attribute.USER);

        String paramGameId = req.getParameter(Attribute.GAME_ID);

        long gameId;
        try {
            gameId = Long.parseLong(paramGameId);
        } catch (NumberFormatException e) {
            // TODO replace for display error / redirect?
            throw new AppError("Wrong format gameID - " + paramGameId, e);
        }
        GameDto gameDto = gameService.getGame(gameId);
        currentSession.setAttribute(Attribute.GAME, gameDto);

        GameSessionDto gameSessionDto = gameService.getSession(user, gameId);
        currentSession.setAttribute(Attribute.GAME_SESSION, gameSessionDto);

        Jsp.forward(req, resp, Jsp.GAME_MENU);
    }
}
