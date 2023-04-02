package dev.makos.quest.dto;

import dev.makos.quest.entity.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Builder
@AllArgsConstructor
public class LevelDto {

    private Long id;
    private String name;
    private String image;
    private String description;
    private List<ButtonDto> buttons;
    private GameStatus gameStatus;

}
