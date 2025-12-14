package kg.management.identity.repository.user;

import kg.management.identity.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        RevisionRepository<User, Long, Long>,
        JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
}
