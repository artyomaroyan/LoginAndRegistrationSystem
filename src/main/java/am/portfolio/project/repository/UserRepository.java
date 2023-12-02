package am.portfolio.project.repository;

import am.portfolio.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Artyom Aroyan
 */

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByUsername(@Param("username") final String username);

    boolean existsByEmail(@Param("email") final String email);
}
