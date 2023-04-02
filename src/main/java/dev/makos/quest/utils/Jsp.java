package dev.makos.quest.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class Jsp {

    public final String MENU = "menu.jsp";
    public final String LOGIN = "login.jsp";
    public final String SIGNUP = "signup.jsp";
    public final String ACCOUNTS = "accounts.jsp";
    public final String PROFILE = "profile.jsp";
    public final String UPDATE = "update.jsp";
    public final String PLAY = "play.jsp";
    public final String GAME_MENU = "game-menu.jsp";
    public final String REQUIREMENT = "requirement.jsp";
    public final String LEVEL = "level.jsp";
    public final String EDIT = "edit.jsp";

    private final String PATH_TO_JSP = "/WEB-INF/jsp/";

    public void forward(HttpServletRequest request, HttpServletResponse response, String filename) throws ServletException, IOException {
        request.getRequestDispatcher(PATH_TO_JSP + filename).forward(request, response);
    }
}
