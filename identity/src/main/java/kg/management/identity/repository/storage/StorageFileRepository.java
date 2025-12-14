package kg.management.identity.repository.storage;

import kg.management.identity.entity.storage.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageFileRepository extends JpaRepository<StorageFile, Long>, JpaSpecificationExecutor<StorageFile> {
}
