package dev.makos.quest.controller.website;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.UserDto;
import dev.makos.quest.entity.Role;
import dev.makos.quest.service.UserService;
import dev.makos.quest.utils.Attribute;
import dev.makos.quest.utils.Go;
import dev.makos.quest.utils.Jsp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static dev.makos.quest.utils.ErrorMessage.*;
import static java.util.Objects.nonNull;

@WebServlet(name = "UpdateServlet", value = Go.EDIT_USER)
public class UpdateServlet extends HttpServlet {

    private final UserService userService = Container.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Attribute.LOGIN); //TODO Duplicate code with Login/Signup Servlet
        Optional<UserDto> user = userService.findByLogin(login);

        if (user.isEmpty()) {
            req.setAttribute(Attribute.ERROR, USER_NOT_FOUND);
            Jsp.forward(req, resp, Jsp.UPDATE);
            return;
        } else {
            UserDto userDto = user.get();
            req.setAttribute(Attribute.USER_UPDATE, userDto);
        }

        Jsp.forward(req, resp, Jsp.UPDATE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginNew = req.getParameter(Attribute.LOGIN_NEW); //TODO Duplicate code with Login/Signup Servlet
        if (nonNull(loginNew) && !loginNew.isBlank()) {
            boolean validLogin = userService.validateLogin(loginNew);
            if (!validLogin) {
                req.setAttribute(Attribute.ERROR, LOGIN_NOT_VALID);
                Jsp.forward(req, resp, Jsp.UPDATE);
                return;
            }
        }

        String password = req.getParameter(Attribute.PASSWORD); //TODO Validate in filter log/pass with AuthService?

        if (nonNull(password) && !password.isBlank()) {
            boolean validPass = userService.validatePassword(password);
            if (!validPass) {
                req.setAttribute(Attribute.ERROR, PASSWORD_NOT_VALID);
                Jsp.forward(req, resp, Jsp.UPDATE);
                return;
            }
        }

        Optional<UserDto> userCheck = userService.findByLogin(loginNew);
        if (userCheck.isPresent()) {
            req.setAttribute(Attribute.ERROR, LOGIN_ALREADY_USED);
            Jsp.forward(req, resp, Jsp.UPDATE);
            return;
        }

        String roleParameter = req.getParameter(Attribute.ROLE);
        Role userRole = userService.getRole(roleParameter);

        String idParam = req.getParameter(Attribute.ID);
        long id = idParam != null && !idParam.isBlank()
                ? Long.parseLong(idParam)
                : 0L;

        Optional<UserDto> userFromDB = userService.findById(id);

        if (userFromDB.isPresent()) {
            Long idUser = userFromDB.get().getId();
            userService.update(idUser, loginNew, password, userRole);
        } else {
            req.setAttribute(Attribute.ERROR, USER_NOT_FOUND);
            Jsp.forward(req, resp, Jsp.UPDATE);
            return;
        }

        resp.sendRedirect(req.getContextPath() + Go.ACCOUNTS);
    }
}
