package kg.management.identity.repository.person;

import kg.management.identity.entity.person.IdentityDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, Long>, JpaSpecificationExecutor<IdentityDocument> {
}
