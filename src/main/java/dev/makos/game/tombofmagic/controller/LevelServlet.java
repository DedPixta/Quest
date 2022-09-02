package dev.makos.game.tombofmagic.controller;

import dev.makos.game.tombofmagic.entity.Element;
import dev.makos.game.tombofmagic.entity.Level;
import dev.makos.game.tombofmagic.service.GameService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/level")
public class LevelServlet extends HttpServlet {

    private final GameService gameService = new GameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();

        String levelIdString = (String) currentSession.getAttribute("levelId");

        int levelId = Integer.parseInt(levelIdString);

        Element element = (Element) currentSession.getAttribute("element");

        int button = req.getParameter("button") != null
                ? Integer.parseInt(req.getParameter("button"))
                : 0;

        Level nextLevel = levelId == 0
                ? gameService.getLevel(levelId)
                : gameService.getNextLevel(element, levelId, button);

        req.setAttribute("level", nextLevel);
        currentSession.setAttribute("levelId", nextLevel.getId());
        currentSession.setAttribute("levelName", nextLevel.getName());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/level.jsp");
        requestDispatcher.forward(req, resp);
    }
}
