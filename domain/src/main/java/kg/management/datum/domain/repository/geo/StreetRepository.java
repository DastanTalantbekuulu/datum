package kg.management.datum.domain.repository.geo;

import kg.management.datum.domain.entity.geo.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>, JpaSpecificationExecutor<Street> {
}
