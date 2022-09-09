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
import java.util.Objects;

@WebServlet("/element")
public class ElementServlet extends HttpServlet {
    public static final int START_LEVEL = 0;
    private final GameService gameService = GameService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonParam = req.getParameter("button");

        if (Objects.isNull(buttonParam)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/element.jsp");
            requestDispatcher.forward(req, resp);
        }

        int button = Integer.parseInt(buttonParam);
        HttpSession currentSession = req.getSession();
        Element element = switch(button) {
            case 0 -> Element.WATER;
            case 1 -> Element.EARTH;
            default -> Element.FIRE;
        };
        currentSession.setAttribute("element", element);
        if (currentSession.getAttribute("levelId") == null) {
            currentSession.setAttribute("levelId", START_LEVEL);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/level");
        requestDispatcher.forward(req, resp);
    }
}
