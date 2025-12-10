package kg.management.datum.domain.repository.dictionary;

import kg.management.datum.domain.entity.dictionary.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationTypeRepository extends JpaRepository<RelationType, String>, JpaSpecificationExecutor<RelationType> {
}
