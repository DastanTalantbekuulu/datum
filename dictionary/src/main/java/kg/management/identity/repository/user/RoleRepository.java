package kg.management.identity.repository.user;

import kg.management.identity.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
}
