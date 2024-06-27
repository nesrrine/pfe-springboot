package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.jwt_spring.identification.Entities.Infrastructurestype;

public interface InfrastructureTypeRepository extends JpaRepository<Infrastructurestype, Long> {

}
