package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.DonneeGenerale;
import pfe.jwt_spring.identification.Entities.Equipement_Publics_type;

@Repository

public interface EquipementPublicTypeRepository extends JpaRepository<Equipement_Publics_type, Long>  {
}
