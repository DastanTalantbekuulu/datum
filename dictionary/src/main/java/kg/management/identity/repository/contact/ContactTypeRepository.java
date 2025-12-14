package kg.management.identity.repository.contact;

import kg.management.identity.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, String>, JpaSpecificationExecutor<ContactType> {
}
