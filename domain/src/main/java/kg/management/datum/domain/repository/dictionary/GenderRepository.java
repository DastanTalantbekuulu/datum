package kg.management.datum.domain.repository.dictionary;

import kg.management.datum.domain.entity.dictionary.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, String>, JpaSpecificationExecutor<Gender> {
}
