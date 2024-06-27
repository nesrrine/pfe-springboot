package pfe.jwt_spring.identification.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.ProgrammeIntervention;
import pfe.jwt_spring.identification.repository.ProgrammeInterventionRepository;

import java.util.List;

@Service
public class ProgrammeInterventionService {

    @Autowired
    private ProgrammeInterventionRepository repository;

    public ProgrammeIntervention addIntervention(ProgrammeIntervention intervention) {
        return repository.save(intervention);
    }

    public List<ProgrammeIntervention> getAllInterventions() {
        return repository.findAll();
    }

    public double calculateTotalCost() {
        return repository.findAll().stream().mapToDouble(intervention -> intervention.getQuantites() * intervention.getCout()).sum();
    }
}
