package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Commune;
import pfe.jwt_spring.identification.Entities.Delegation;
import pfe.jwt_spring.identification.Entities.Logement;

import java.util.List;

@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {
    List<Delegation> findByGouvernoratId(Long id);
}

