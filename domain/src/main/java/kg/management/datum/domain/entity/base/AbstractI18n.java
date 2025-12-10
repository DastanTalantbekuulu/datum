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
public abstract class AbstractI18n<PK> extends AbstractAuditMutable<PK> {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "i18n", columnDefinition = "jsonb", nullable = false)
    @Builder.Default
    private Map<String, String> i18n = new HashMap<>();

    public String getName(String langCode) {
        if (i18n == null || i18n.isEmpty()) {
            return null;
        }
        return i18n.getOrDefault(langCode, i18n.get("en"));
    }

    public void addTranslation(String lang, String name) {
        if (i18n == null) {
            i18n = new HashMap<>();
        }
        i18n.put(lang, name);
    }
}
