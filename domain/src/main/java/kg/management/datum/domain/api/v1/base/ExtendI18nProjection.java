package kg.management.datum.domain.api.v1.base;

import java.util.Map;

public interface ExtendI18nProjection<PK> extends I18nProjection<PK> {
    Map<String, String> getExtendI18n();
}