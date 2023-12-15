package am.portfolio.project.repository;

import am.portfolio.project.enums.RoleEnum;
import am.portfolio.project.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Artyom Aroyan
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findRoleByName(@Param("name") RoleEnum name);
}
