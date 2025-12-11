package kg.management.datum.domain.entity.person;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.RichLocalizedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "gender")
@AttributeOverride(name = "id", column = @Column(name = "code", nullable = false,
        check = @CheckConstraint(constraint = "code IN ('M', 'F', 'X')")))
@AttributeOverride(name = "extendI18n", column = @Column(name = "short_i18n", columnDefinition = "jsonb", nullable = false))
public class Gender extends RichLocalizedEntity<String> {

    @Getter
    @RequiredArgsConstructor
    public enum Code {
        M(1, 'M'),
        F(2, 'F'),
        X(3, 'X');

        private final int code;
        private final char value;

        public static Code fromValue(String value) {
            if (value == null) return null;
            try {
                return Code.valueOf(value);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    @Converter(autoApply = true)
    public static class CodeConverter implements AttributeConverter<Code, String> {
        @Override
        public String convertToDatabaseColumn(Code attribute) {
            return attribute == null ? null : attribute.name();
        }

        @Override
        public Code convertToEntityAttribute(String dbData) {
            return Code.fromValue(dbData);
        }
    }
}