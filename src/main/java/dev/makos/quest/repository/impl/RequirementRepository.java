package dev.makos.quest.repository.impl;

import dev.makos.quest.entity.Requirement;
import dev.makos.quest.repository.BaseRepository;
import dev.makos.quest.repository.SessionCreator;

public class RequirementRepository extends BaseRepository<Requirement> {

    public RequirementRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Requirement.class);
    }
}
