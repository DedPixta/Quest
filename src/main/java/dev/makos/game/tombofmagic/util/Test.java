package dev.makos.game.tombofmagic.util;

import dev.makos.game.tombofmagic.repository.LevelRepository;
import dev.makos.game.tombofmagic.repository.Repository;

public class Test {
    public static void main(String[] args) {
        Repository levelRepository = LevelRepository.getInstance();

        System.out.println(levelRepository.getGameMap());
    }
}
