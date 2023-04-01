package dev.makos.game.tombofmagic.repository.impl;

import dev.makos.game.tombofmagic.entity.Requirement;
import dev.makos.game.tombofmagic.repository.BaseRepository;
import dev.makos.game.tombofmagic.repository.SessionCreator;

public class RequirementRepository extends BaseRepository<Requirement> {

    public RequirementRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Requirement.class);
    }
}
