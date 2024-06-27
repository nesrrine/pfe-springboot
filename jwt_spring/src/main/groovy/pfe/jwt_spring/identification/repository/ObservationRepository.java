package pfe.jwt_spring.identification.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Observation;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
}