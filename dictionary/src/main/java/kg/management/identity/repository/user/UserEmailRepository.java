package kg.management.identity.repository.user;

import kg.management.identity.entity.user.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, UserEmail.Id>, JpaSpecificationExecutor<UserEmail> {
}
