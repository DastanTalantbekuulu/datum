package kg.management.datum.domain.entity.person;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@AttributeOverride(name = "id", column = @Column(name = "code", length = 1, nullable = false, columnDefinition = "char(1) CHECK (code IN ('M', 'F', 'X'))"))
@AttributeOverride(name = "extendI18n", column = @Column(name = "short_i18n", columnDefinition = "jsonb", nullable = false))
public class Gender extends RichLocalizedEntity<Gender.Code> {

    @Getter
    @RequiredArgsConstructor
    public enum Code {
        M(1, 'M'),
        F(2, 'F'),
        X(3, 'X');
        private final int code;
        private final char value;
    }

    @Override
    @Enumerated(EnumType.STRING)
    public Code getId() {
        return super.getId();
    }

    @Override
    public void setId(Code id) {
        super.setId(id);
    }
}