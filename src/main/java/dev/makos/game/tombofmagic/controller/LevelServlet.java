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

    private final GameService gameService = GameService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();

        int levelId = (int) currentSession.getAttribute("levelId");
        Element element = (Element) currentSession.getAttribute("element");

        String button = req.getParameter("button");

        Level nextLevel;
        if (button == null){
            nextLevel = gameService.getLevel(levelId);
        } else {
            int buttonId = Integer.parseInt(req.getParameter("button"));
            nextLevel = gameService.getNextLevel(element, levelId, buttonId);
        }
        if (nextLevel == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/element");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("level", nextLevel);
            currentSession.setAttribute("levelId", nextLevel.getId());
            currentSession.setAttribute("levelName", nextLevel.getName());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/level.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
