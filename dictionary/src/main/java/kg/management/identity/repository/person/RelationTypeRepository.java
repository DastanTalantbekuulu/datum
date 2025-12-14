package kg.management.identity.repository.person;

import kg.management.identity.entity.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationTypeRepository extends JpaRepository<RelationType, String>, JpaSpecificationExecutor<RelationType> {
}
