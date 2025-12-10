package kg.management.datum.domain.repository.person;

import kg.management.datum.domain.entity.person.PersonPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPhotoRepository extends JpaRepository<PersonPhoto, Long>, JpaSpecificationExecutor<PersonPhoto> {
}
