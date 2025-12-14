package kg.management.identity.repository.person;

import kg.management.identity.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, String>, JpaSpecificationExecutor<Gender> {
}
