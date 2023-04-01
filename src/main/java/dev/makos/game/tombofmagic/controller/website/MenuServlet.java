package dev.makos.game.tombofmagic.controller.website;

import dev.makos.game.tombofmagic.entity.Role;
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

import static java.util.Objects.isNull;

@WebServlet(name = "MenuServlet", value = {Go.MENU, Go.HOME})
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String roleAttribute = Attribute.ROLE;
        Role role = (Role) session.getAttribute(roleAttribute);
        if (isNull(role)) {
            session.setAttribute(roleAttribute, Role.GUEST);
        } else {
            session.setAttribute(roleAttribute, role);
        }
        Jsp.forward(req, resp, Jsp.MENU);
    }
}
