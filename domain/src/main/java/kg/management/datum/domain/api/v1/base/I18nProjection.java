package kg.management.datum.domain.api.v1.base;

import java.util.Map;

public interface I18nProjection<PK> extends AuditProjection<PK> {
    Map<String, String> getI18n();

    // Можно добавить удобный метод через SpEL, чтобы сразу отдавать имя на текущем языке
    // @Value("#{target.getName(T(org.springframework.context.i18n.LocaleContextHolder).getLocale().getLanguage())}")
    // String getCurrentLocaleName();
}