package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Commune;
import pfe.jwt_spring.identification.Entities.CouvertureReseaux;
import pfe.jwt_spring.identification.Entities.Delegation;
import pfe.jwt_spring.identification.Entities.Quartier;

import java.util.List;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {
    List<Commune> findByDelegationId(Long id);

}



