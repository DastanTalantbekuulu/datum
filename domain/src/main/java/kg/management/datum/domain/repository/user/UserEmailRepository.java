package kg.management.datum.domain.repository.user;

import kg.management.datum.domain.entity.user.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, UserEmail.Id>, JpaSpecificationExecutor<UserEmail> {
}
