package kg.management.datum.domain.repository.contact;

import kg.management.datum.domain.entity.contact.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, UserEmail.Id>, JpaSpecificationExecutor<UserEmail> {
}
