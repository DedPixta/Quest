package dev.makos.game.tombofmagic.repository;

import dev.makos.game.tombofmagic.entity.Level;

import java.util.Map;

public interface Repository {

    Map<Integer, Level> getGameMap();
}
