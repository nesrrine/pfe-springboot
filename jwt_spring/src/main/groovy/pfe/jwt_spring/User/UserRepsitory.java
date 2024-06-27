package pfe.jwt_spring.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepsitory extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    List<User> findAll();

}
