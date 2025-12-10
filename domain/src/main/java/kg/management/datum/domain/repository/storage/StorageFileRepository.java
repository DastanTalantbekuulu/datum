package kg.management.datum.domain.repository.storage;

import kg.management.datum.domain.entity.storage.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageFileRepository extends JpaRepository<StorageFile, Long>, JpaSpecificationExecutor<StorageFile> {
}
