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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static dev.makos.game.tombofmagic.utils.ErrorMessage.*;

@WebServlet(name = "LoginServlet", value = Go.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = Container.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, Jsp.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Attribute.LOGIN); //TODO Duplicate code with SignupServlet
        boolean validLogin = userService.validateLogin(login);
        if (!validLogin) {
            req.setAttribute(Attribute.ERROR, LOGIN_NOT_VALID);
            Jsp.forward(req, resp, Jsp.LOGIN);
        }

        String password = req.getParameter(Attribute.PASSWORD); //TODO Validate in filter log/pass with AuthService?
        boolean validPass = userService.validatePassword(password);
        if (!validPass) {
            req.setAttribute(Attribute.ERROR, PASSWORD_NOT_VALID);
            Jsp.forward(req, resp, Jsp.LOGIN);
        }

        Optional<UserDto> user = userService.findByLogin(login);

        if (user.isPresent()) {
            Optional<UserDto> userDtoFromDB = userService.findByCredentials(login, password);

            if (userDtoFromDB.isPresent()) {
                HttpSession session = req.getSession();
                UserDto userDto = userDtoFromDB.get();
                session.setAttribute(Attribute.USER, userDto);
                session.setAttribute(Attribute.ROLE, userDto.getRole());
                Jsp.forward(req, resp, Jsp.MENU);
            } else {
                req.setAttribute(Attribute.ERROR, WRONG_PASSWORD);
                Jsp.forward(req, resp, Jsp.LOGIN);
            }
        } else {
            req.setAttribute(Attribute.ERROR, USER_NOT_FOUND);
            Jsp.forward(req, resp, Jsp.LOGIN);
        }
    }
}
