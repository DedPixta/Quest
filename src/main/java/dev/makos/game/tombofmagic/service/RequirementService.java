package dev.makos.game.tombofmagic.service;

import dev.makos.game.tombofmagic.config.Container;
import dev.makos.game.tombofmagic.entity.Requirement;
import dev.makos.game.tombofmagic.repository.impl.RequirementRepository;

import java.util.ArrayList;
import java.util.List;

public class RequirementService {

    private final RequirementRepository requirementRepository = Container.getInstance(RequirementRepository.class);

    // TODO create edit game feature
    public List<Requirement> createRequirements(List<String> reqs) {
        List<Requirement> requirements = new ArrayList<>();
        for (String name : reqs) {
            Requirement requirement = Requirement.builder()
                    .name(name)
                    .build();
            requirementRepository.create(requirement);
            requirements.add(requirement);
        }
        return requirements;
    }

    public Requirement getById(long requirementId) {
        return requirementRepository.getById(requirementId);
    }
}
