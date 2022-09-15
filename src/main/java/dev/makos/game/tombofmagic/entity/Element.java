package dev.makos.game.tombofmagic.entity;

public enum Element {
    NONE("Отсутствует"),
    WATER("Вода"),
    EARTH("Земля"),
    FIRE("Огонь");

    private final String name;

    private Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
