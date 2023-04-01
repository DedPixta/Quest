package dev.makos.game.tombofmagic.dto;

import dev.makos.game.tombofmagic.entity.GameStatus;
import dev.makos.game.tombofmagic.entity.Requirement;
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
