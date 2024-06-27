package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Delegation;
import pfe.jwt_spring.identification.Entities.Gouvernorat;
import pfe.jwt_spring.identification.Entities.Programme;

@Repository
public interface GouvernoratRepository extends JpaRepository<Gouvernorat, Long> {
}
