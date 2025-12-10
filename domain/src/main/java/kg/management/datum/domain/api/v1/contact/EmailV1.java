package kg.management.datum.domain.api.v1.contact;

import kg.management.datum.domain.api.v1.base.AuditProjection;
import kg.management.datum.domain.entity.contact.Email;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "v1", types = Email.class)
public interface EmailV1 extends AuditProjection<Long> {
    String getAddress();
}