package kg.management.identity.repository.person;

import kg.management.identity.entity.person.PersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRelationRepository extends JpaRepository<PersonRelation, Long>, JpaSpecificationExecutor<PersonRelation> {
}
