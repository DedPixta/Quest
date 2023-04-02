package dev.makos.quest.controller.game;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.GameDto;
import dev.makos.quest.dto.GameSessionDto;
import dev.makos.quest.entity.Requirement;
import dev.makos.quest.service.GameService;
import dev.makos.quest.service.RequirementService;
import dev.makos.quest.utils.Attribute;
import dev.makos.quest.utils.ErrorMessage;
import dev.makos.quest.utils.Go;
import dev.makos.quest.utils.Jsp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "RequirementServlet", value = Go.REQUIREMENT)
public class RequirementServlet extends HttpServlet {

    private final RequirementService requirementService = Container.getInstance(RequirementService.class);
    private final GameService gameService = Container.getInstance(GameService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        String paramId = req.getParameter(Attribute.ID);

        if (Objects.isNull(paramId)) {
            Jsp.forward(req, resp, Jsp.REQUIREMENT);
            return;
        }

        long requirementId;
        try {
            requirementId = Long.parseLong(paramId);
        } catch (NumberFormatException e) {
            currentSession.setAttribute(Attribute.ERROR, ErrorMessage.REQ_ID_NOT_FOUND);
            Jsp.forward(req, resp, Jsp.REQUIREMENT);
            return;
        }
        Requirement requirement = requirementService.getById(requirementId);
        GameSessionDto gameSessionDto = (GameSessionDto) currentSession.getAttribute(Attribute.GAME_SESSION);
        gameService.updateRequirement(gameSessionDto, requirement);

        GameDto gameDto = (GameDto) currentSession.getAttribute(Attribute.GAME);
        gameService.updateLevel(gameSessionDto, gameDto.getStartLevelId());
        Jsp.forward(req, resp, Jsp.LEVEL);
    }
}
