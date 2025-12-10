package kg.management.datum.domain.api.v1.base;

import java.time.Instant;

public interface AuditProjection<PK> extends IdentifiableProjection<PK> {
    Instant getCreatedAt();
    Instant getUpdatedAt();
}