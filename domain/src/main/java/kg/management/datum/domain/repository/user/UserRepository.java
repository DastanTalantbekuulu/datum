package kg.management.datum.domain.repository.user;

import kg.management.datum.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "user", itemResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long>,
        RevisionRepository<User, Long, Long>,
        JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
}
