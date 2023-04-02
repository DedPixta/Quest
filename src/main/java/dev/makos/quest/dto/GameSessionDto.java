package dev.makos.quest.dto;

import dev.makos.quest.entity.GameStatus;
import dev.makos.quest.entity.Requirement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Builder
@AllArgsConstructor
public class GameSessionDto {

    private Long id;
    private Long userId;
    private Long gameId;
    private Long currentQuestionId;
    private Requirement requirement;
    private GameStatus gameStatus;

}
