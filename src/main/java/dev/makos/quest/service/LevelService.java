package dev.makos.quest.service;

import dev.makos.quest.config.Container;
import dev.makos.quest.dto.ButtonDto;
import dev.makos.quest.dto.LevelDto;
import dev.makos.quest.entity.Button;
import dev.makos.quest.entity.Level;
import dev.makos.quest.entity.Requirement;
import dev.makos.quest.mapper.LevelMapper;
import dev.makos.quest.repository.impl.ButtonRepository;
import dev.makos.quest.repository.impl.LevelRepository;

import java.util.Comparator;
import java.util.List;

public class LevelService {

    private final LevelRepository levelRepository = Container.getInstance(LevelRepository.class);
    private final ButtonRepository buttonRepository = Container.getInstance(ButtonRepository.class);
    private final ButtonService buttonService = new ButtonService();
    private final LevelMapper levelMapper = new LevelMapper();


    public LevelDto getLevel(Long levelId, Requirement requirement) {
        buttonRepository.startTransaction();
        try {
            Level level = levelRepository.getById(levelId);
            LevelDto levelDto = levelMapper.toDto(level);
            List<ButtonDto> buttonDtos = buttonRepository.getAllByLevelId(levelId)
                    .map(button -> buttonService.getButtonsByRequirement(button, requirement))
                    .toList();
            levelDto.setButtons(buttonDtos);
            return levelDto;
        } finally {
            buttonRepository.endTransaction();
        }
    }

    public Long getNextLevelId(Long levelId, Requirement requirement, int buttonNumber) {
        List<Button> buttons = buttonRepository.getAllByLevelId(levelId)
                .sorted(Comparator.comparing(Button::getId))
                .toList();
        Button button = buttons.get(buttonNumber);

        return buttonService.getNextLevelId(button, requirement);
    }
}
