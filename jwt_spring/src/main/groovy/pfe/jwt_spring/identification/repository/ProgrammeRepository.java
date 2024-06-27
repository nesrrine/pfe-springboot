package pfe.jwt_spring.identification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.jwt_spring.identification.Entities.Programme;

import java.util.List;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    List<Programme> findAllByIdGreaterThan(Long id);

}
