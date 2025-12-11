package kg.management.datum.domain.repository.person;

import kg.management.datum.domain.entity.person.PersonPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPhoneRepository extends JpaRepository<PersonPhone,PersonPhone.Id>, JpaSpecificationExecutor<PersonPhone> {
}
