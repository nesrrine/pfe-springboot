package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.DonneeGenerale;

@Repository
public interface DonneesGeneralesRepository extends JpaRepository<DonneeGenerale, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
