package dev.makos.quest.dto;

import dev.makos.quest.entity.Requirement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Builder
@AllArgsConstructor
public class GameDto {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Long authorId;
    private Long startLevelId;
    private String requirementDescription;
    private List<Requirement> requirements;

}
