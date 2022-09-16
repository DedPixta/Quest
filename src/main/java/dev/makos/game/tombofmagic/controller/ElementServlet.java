package dev.makos.game.tombofmagic.controller;

import dev.makos.game.tombofmagic.entity.Element;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String elementParam = req.getParameter("element");

        if (Objects.isNull(elementParam)) {
            resp.sendRedirect("element?element=0");
        } else {
            int elementNumber = Integer.parseInt(elementParam);
            Element element;
            switch (elementNumber) {
                case 1:
                    element = Element.WATER;
                    break;
                case 2:
                    element = Element.EARTH;
                    break;
                case 3:
                    element = Element.FIRE;
                    break;
                default:
                    element = Element.NONE;
            }
            ;

            if (Element.NONE == element) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/element.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                HttpSession currentSession = req.getSession();
                currentSession.setAttribute("element", element);
                currentSession.setAttribute("levelId", START_LEVEL);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/level");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
