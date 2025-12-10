package kg.management.datum.domain.repository.dictionary;

import kg.management.datum.domain.entity.dictionary.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, String>, JpaSpecificationExecutor<ContactType> {
}
