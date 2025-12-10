package kg.management.datum.domain.api.v1.base;

import org.springframework.beans.factory.annotation.Value;

public interface FullAuditProjection<PK> extends AuditProjection<PK> {
    @Value("#{target.createdBy?.username}")
    String getCreatedBy();

    @Value("#{target.updatedBy?.username}")
    String getUpdatedBy();
}