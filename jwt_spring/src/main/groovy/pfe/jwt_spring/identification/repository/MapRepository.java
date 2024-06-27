package pfe.jwt_spring.identification.repository;

import org.apache.logging.log4j.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Circle;

@Repository
public interface MapRepository extends JpaRepository<Marker, Long> {
}