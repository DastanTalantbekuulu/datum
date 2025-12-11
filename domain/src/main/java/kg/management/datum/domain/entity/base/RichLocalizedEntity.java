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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
public abstract class RichLocalizedEntity<PK extends Serializable> extends LocalizedEntity<PK> {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "extend_i18n", columnDefinition = "jsonb", nullable = false)
    @Builder.Default
    private Map<String, String> extendI18n = new HashMap<>();

    public String getExtendName(String langCode) {  // todo replace string to enum lang iso
        if (extendI18n == null || extendI18n.isEmpty()) {
            return null;
        }
        return extendI18n.getOrDefault(langCode, extendI18n.get("en"));
    }

    public void addTranslation(String lang, String name, String extend) {  // todo replace string to enum lang iso
        if (extendI18n == null) extendI18n = new HashMap<>();

        super.addTranslation(lang, name);
        extendI18n.put(lang, extend);
    }
}
