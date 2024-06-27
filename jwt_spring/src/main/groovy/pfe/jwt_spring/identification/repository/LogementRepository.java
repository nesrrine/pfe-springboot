package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Logement;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {
    // Vous pouvez ajouter des méthodes personnalisées pour les requêtes de recherche spécifiques si nécessaire
}
