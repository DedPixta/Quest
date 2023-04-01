package dev.makos.game.tombofmagic.filter;

import dev.makos.game.tombofmagic.dto.UserDto;
import dev.makos.game.tombofmagic.entity.Role;
import dev.makos.game.tombofmagic.error.AppError;
import dev.makos.game.tombofmagic.utils.Attribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.makos.game.tombofmagic.utils.Go.*;


@WebFilter(filterName = "RoleSelector", value = {ROOT, LOGIN, SIGNUP, LOGOUT, PROFILE, ACCOUNTS, ACCOUNTS_DELETE, EDIT_USER, PLAY, EDIT_GAME, MENU, GAME, REQUIREMENT, LEVEL})
public class RoleSelectorFilter implements Filter {

    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of(ROOT, LOGIN, SIGNUP, MENU),
            Role.USER, List.of(ROOT, LOGIN, SIGNUP, LOGOUT, PROFILE, ACCOUNTS, PLAY, MENU, GAME, REQUIREMENT, LEVEL),
            Role.EDITOR, List.of(ROOT, LOGIN, SIGNUP, LOGOUT, PROFILE, ACCOUNTS, PLAY, MENU, GAME, REQUIREMENT, LEVEL, EDIT_GAME),
            Role.ADMIN, List.of(ALL)
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Object user = request.getSession().getAttribute(Attribute.USER);
        Role role = (Objects.isNull(user))
                ? Role.GUEST
                : ((UserDto) user).getRole();
        String command = getCommand(request);
        if (uriMap.get(role).contains(command)) {
            filterChain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + LOGIN);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile(".*(/[a-z-]*)").matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new AppError("incorrect uri: " + uri);
        }
    }
}
