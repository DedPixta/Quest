package dev.makos.game.tombofmagic.service;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.dto.ButtonDto;
import dev.makos.game.tombofmagic.dto.LevelDto;
import dev.makos.game.tombofmagic.entity.Button;
import dev.makos.game.tombofmagic.entity.Level;
import dev.makos.game.tombofmagic.entity.Requirement;
import dev.makos.game.tombofmagic.mapper.LevelMapper;
import dev.makos.game.tombofmagic.repository.impl.ButtonRepository;
import dev.makos.game.tombofmagic.repository.impl.LevelRepository;

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
