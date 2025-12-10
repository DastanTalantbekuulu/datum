package kg.management.datum.domain.api.v1.user;

import kg.management.datum.domain.api.v1.base.I18nProjection;
import kg.management.datum.domain.entity.user.Role;
import org.springframework.data.rest.core.config.Projection;

import java.util.Map;

@Projection(name = "v1", types = Role.class)
public interface RoleV1 extends I18nProjection<String> {
    Map<String, String> getExtendI18n();
}