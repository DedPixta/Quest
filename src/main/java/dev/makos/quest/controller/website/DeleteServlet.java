package dev.makos.quest.controller.website;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.UserDto;
import dev.makos.quest.service.UserService;
import dev.makos.quest.utils.Attribute;
import dev.makos.quest.utils.ErrorMessage;
import dev.makos.quest.utils.Go;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.isNull;

@WebServlet(name = "DeleteServlet", value = Go.ACCOUNTS_DELETE)
public class DeleteServlet extends HttpServlet {

    private final UserService userService = Container.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Attribute.LOGIN);
        if (isNull(login)) {
            req.setAttribute(Attribute.ERROR, ErrorMessage.USER_NOT_FOUND);
            req.getRequestDispatcher("/accounts").forward(req, resp);
            return;
        }

        UserDto currentUser = (UserDto) req.getSession().getAttribute(Attribute.USER);
        String userLogin = currentUser.getLogin();
        if (login.equals(userLogin)) {
            req.setAttribute(Attribute.ERROR, ErrorMessage.DELETE_YOURSELF);
            req.getRequestDispatcher("/accounts").forward(req, resp);
            return;
        }

        Optional<UserDto> user = userService.findByLogin(login);
        user.ifPresent(userDto -> userService.deleteById(userDto.getId()));
        req.getRequestDispatcher("/accounts").forward(req, resp);
    }
}
