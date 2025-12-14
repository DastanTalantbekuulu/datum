package kg.management.identity.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.common.entity.base.LocalizedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "language")
@AttributeOverride(name = "id", column = @Column(name = "language", length = 2, columnDefinition = "CHAR(2)",
        nullable = false, updatable = false, comment = "ISO 639-1"))
public class Language extends LocalizedEntity<String> {

    @Column(name = "iso_639_2", length = 3, comment = "ISO 639-2", columnDefinition = "CHAR(3)")
    @Size(max = 3)
    private String iso6392;

    @Column(name = "iso_639_3", length = 3, comment = "ISO 639-3", columnDefinition = "CHAR(3)")
    @Size(max = 3)
    private String iso6393;

    @Column(name = "name_en", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nameEn;

    @Column(name = "native", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String nameNative;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "direction", columnDefinition = "CHAR(3) NOT NULL DEFAULT 'LTR'",
            check = @CheckConstraint(constraint = "direction IN ('LTR', 'RTL')"))
    @Builder.Default
    private TextDirection direction = TextDirection.LTR;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;

    @Column(name = "emoji", length = 10)
    @Size(max = 10)
    private String emoji;

    @Column(name = "script", length = 4, columnDefinition = "CHAR(4)", comment = "ISO 15924")
    @Size(max = 4)
    private String script;

    public enum TextDirection {
        LTR, RTL
    }
}