package kg.management.identity.repository.contact;

import kg.management.identity.entity.contact.EmailDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDomainRepository extends JpaRepository<EmailDomain, Long>, JpaSpecificationExecutor<EmailDomain> {
}
