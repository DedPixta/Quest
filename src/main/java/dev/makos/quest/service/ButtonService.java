package dev.makos.quest.service;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.ButtonDto;
import dev.makos.quest.entity.Button;
import dev.makos.quest.entity.Requirement;
import dev.makos.quest.repository.impl.RequirementRepository;

import java.util.Objects;

import static java.util.Objects.isNull;

public class ButtonService {

    private final RequirementRepository requirementRepository = Container.getInstance(RequirementRepository.class);

    private String getDescription(Button button, Requirement requirement) {
        if (isNull(button.getRequirement())) {
            return button.getMainDescription();
        }

        Requirement buttonRequirement = requirementRepository.getById(button.getRequirement().getId());
        return Objects.equals(buttonRequirement, requirement)
                ? button.getMainDescription()
                : button.getAltDescription();
    }

    public Long getNextLevelId(Button button, Requirement requirement) {
        if (isNull(button.getRequirement())) {
            return button.getMainLevel().getId();
        }

        Requirement buttonRequirement = requirementRepository.getById(button.getRequirement().getId());
        return Objects.equals(buttonRequirement.getId(), requirement.getId())
                ? button.getMainLevel().getId()
                : button.getAltLevel().getId();
    }

    public ButtonDto getButtonsByRequirement(Button button, Requirement requirement) {
        return ButtonDto.builder()
                .id(button.getId())
                .description(getDescription(button, requirement))
                .levelId(getNextLevelId(button, requirement))
                .build();
    }
}
