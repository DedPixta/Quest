package dev.makos.game.tombofmagic.controller.website;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.GameDto;
import dev.makos.game.tombofmagic.service.PlayService;
import dev.makos.game.tombofmagic.utils.Attribute;
import dev.makos.game.tombofmagic.utils.Go;
import dev.makos.game.tombofmagic.utils.Jsp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "PlayServlet", value = Go.PLAY)
public class PlayServlet extends HttpServlet {

    private final PlayService playService = Container.getInstance(PlayService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<GameDto> games = playService.getAll();
        req.setAttribute(Attribute.GAMES, games);
        Jsp.forward(req, resp, Jsp.PLAY);
    }
}
