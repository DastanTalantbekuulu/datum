package kg.management.datum.domain.repository.contact;

import kg.management.datum.domain.entity.contact.PhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCodeRepository extends JpaRepository<PhoneCode, Long>, JpaSpecificationExecutor<PhoneCode> {
}
