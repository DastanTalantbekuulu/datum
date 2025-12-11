package kg.management.datum.domain.repository.person;

import kg.management.datum.domain.entity.person.PersonEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonEmailRepository extends JpaRepository<PersonEmail, PersonEmail.Id>, JpaSpecificationExecutor<PersonEmail> {
}
