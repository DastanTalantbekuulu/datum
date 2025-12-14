package kg.management.identity.repository.person;

import kg.management.identity.entity.person.PersonPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPhotoRepository extends JpaRepository<PersonPhoto, Long>, JpaSpecificationExecutor<PersonPhoto> {
}
