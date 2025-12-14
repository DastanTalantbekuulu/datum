package kg.management.identity.repository;

import kg.management.identity.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String>, JpaSpecificationExecutor<Language> {
}
