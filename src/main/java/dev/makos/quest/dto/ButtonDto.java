package dev.makos.quest.dto;

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
