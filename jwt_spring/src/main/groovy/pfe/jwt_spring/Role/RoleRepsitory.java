package pfe.jwt_spring.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleRepsitory extends JpaRepository<Role, Repository> {

    Optional<Role> findByName(String role);
}
