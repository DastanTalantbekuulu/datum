package kg.management.datum.domain.repository;

import kg.management.datum.domain.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String>, JpaSpecificationExecutor<Language> {
}
