package kg.management.identity.repository.person;

import kg.management.identity.entity.person.PersonEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonEmailRepository extends JpaRepository<PersonEmail, PersonEmail.Id>, JpaSpecificationExecutor<PersonEmail> {
}
