package dev.makos.game.tombofmagic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Builder
@AllArgsConstructor
public class ButtonDto {

    private Long id;
    private String description;
    private Long levelId;

}
