package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Quartier;

import java.util.List;

@Repository
public interface QuartierRepository extends JpaRepository<Quartier, Long> {
    List<Quartier> findByCommuneId(long communeId);
}



