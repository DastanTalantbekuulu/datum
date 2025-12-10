package kg.management.datum.domain.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
public class AbstractI18nExtend<PK> extends AbstractI18n<PK> {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "extend_i18n", columnDefinition = "jsonb", nullable = false)
    @Builder.Default
    private Map<String, String> extendI18n = new HashMap<>();

    public String getExtendName(String langCode) {
        if (extendI18n == null || extendI18n.isEmpty()) {
            return null;
        }
        return extendI18n.getOrDefault(langCode, extendI18n.get("en"));
    }

    public void addTranslation(String lang, String name, String extend) {
        if (extendI18n == null) extendI18n = new HashMap<>();

        addTranslation(lang, name);
        extendI18n.put(lang, extend);
    }
}
