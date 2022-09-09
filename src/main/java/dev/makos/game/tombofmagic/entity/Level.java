package dev.makos.game.tombofmagic.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = "id", includeFieldNames = false)
public class Level {

    private int id;
    private String name;
    private String image;
    private String description;
    private List<Button> buttons;

    public int getNextLevelId(Element element, int clickedButton) {
        return buttons.get(clickedButton)
                .getNextLevelId(element);
    }
}
