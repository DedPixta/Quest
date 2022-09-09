package dev.makos.game.tombofmagic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(of = "id", includeFieldNames = false)
@AllArgsConstructor // TODO delete?
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
