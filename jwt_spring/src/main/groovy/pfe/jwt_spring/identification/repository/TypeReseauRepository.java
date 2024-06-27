package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.jwt_spring.identification.Entities.TypeReseau;

public interface TypeReseauRepository extends JpaRepository<TypeReseau, Long>{

    }
