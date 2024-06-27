package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.CouvertureReseaux;

@Repository
public interface CouvertureReseauxRepository extends JpaRepository<CouvertureReseaux, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}
