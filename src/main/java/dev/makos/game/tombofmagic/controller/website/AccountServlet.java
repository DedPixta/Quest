package dev.makos.game.tombofmagic.controller.website;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.UserDto;
import dev.makos.game.tombofmagic.service.UserService;
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

import static java.util.Objects.isNull;

@WebServlet(name = "AccountServlet", value = Go.ACCOUNTS)
public class AccountServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final UserService userService = Container.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNumber = request.getParameter(Attribute.PAGE_NUMBER);
        int pageNumberValue;
        if (isNull(pageNumber)) {
            pageNumberValue = DEFAULT_PAGE_NUMBER;
        } else {
            try {
                pageNumberValue = Integer.parseInt(pageNumber);
            } catch (NumberFormatException e) {
                pageNumberValue = DEFAULT_PAGE_NUMBER;
            }
        }
        request.setAttribute(Attribute.PAGE_NUMBER, pageNumberValue);

        Collection<UserDto> users = userService.getAll(pageNumberValue, DEFAULT_PAGE_SIZE);
        request.setAttribute(Attribute.USERS, users);

        int pages = userService.getAllCount() / DEFAULT_PAGE_SIZE;
        request.setAttribute(Attribute.PAGE_COUNT, pages);

        Jsp.forward(request, response, Jsp.ACCOUNTS);
    }
}
