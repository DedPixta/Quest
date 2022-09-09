package dev.makos.game.tombofmagic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Button {
    private String mainDescription;
    private String altDescription;
    private int mainLevel;
    private int altLevel;

    private Element requirement;

    public Button(String mainDescription, int mainLevel) {
        this.mainDescription = mainDescription;
        this.mainLevel = mainLevel;
    }

    public String getDescription(Element element) {
        if (Objects.isNull(this.requirement)) {
            return mainDescription;
        }

        return this.requirement.equals(element)
                ? mainDescription
                : altDescription;
    }

    public int getNextLevelId(Element element) {
        if (Objects.isNull(this.requirement)) {
            return mainLevel;
        }

        return this.requirement.equals(element)
                ? mainLevel
                : altLevel;
    }
}
