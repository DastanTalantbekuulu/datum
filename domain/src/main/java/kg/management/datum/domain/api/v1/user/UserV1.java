package kg.management.datum.domain.api.v1.user;


import kg.management.datum.domain.api.v1.base.FullAuditProjection;
import kg.management.datum.domain.entity.user.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
import java.util.UUID;

@Projection(name = "v1", types = User.class)
public interface UserV1 extends FullAuditProjection<Long> {

    UUID getExternalId();

    String getUsername();

    boolean isEnabled();

    boolean isLocked();

    List<RoleV1> getRoles();
}