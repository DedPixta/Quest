package dev.makos.game.tombofmagic.dto;

import dev.makos.game.tombofmagic.entity.GameStatus;
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
