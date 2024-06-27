package pfe.jwt_spring.identification.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pfe.jwt_spring.identification.Entities.ProgrammeIntervention;

public interface ProgrammeInterventionRepository extends JpaRepository<ProgrammeIntervention, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
