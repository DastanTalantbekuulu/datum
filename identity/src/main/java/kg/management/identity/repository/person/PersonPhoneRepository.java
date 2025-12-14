package kg.management.identity.repository.person;

import kg.management.identity.entity.person.PersonPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPhoneRepository extends JpaRepository<PersonPhone,PersonPhone.Id>, JpaSpecificationExecutor<PersonPhone> {
}
