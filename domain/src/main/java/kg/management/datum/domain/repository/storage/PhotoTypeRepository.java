package kg.management.datum.domain.repository.storage;

import kg.management.datum.domain.entity.storage.PhotoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoTypeRepository extends JpaRepository<PhotoType, String>, JpaSpecificationExecutor<PhotoType> {
}
