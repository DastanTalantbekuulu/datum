package kg.management.datum.domain.api.v1.contact;

import kg.management.datum.domain.api.v1.base.AuditProjection;
import kg.management.datum.domain.entity.contact.Phone;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "v1", types = Phone.class)
public interface PhoneV1 extends AuditProjection<Long> {
    String getNumber();

    boolean isHasWhatsapp();

    boolean isHasTelegram();
}
