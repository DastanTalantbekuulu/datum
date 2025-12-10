package kg.management.datum.domain.entity.geo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.AbstractI18n;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE country SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Audited
public class Country extends AbstractI18n<Long> {

    @Column(name = "iso_alpha2", nullable = false, length = 2)
    @Size(min = 2, max = 2, message = "ISO Alpha-2 code must be exactly 2 characters")
    @Pattern(regexp = "^[A-Z]{2}$", message = "ISO Alpha-2 must be uppercase letters (e.g. KG)")
    private String isoAlpha2;

    @Column(name = "iso_alpha3", nullable = false, length = 3)
    @Size(min = 3, max = 3, message = "ISO Alpha-3 code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "ISO Alpha-3 must be uppercase letters (e.g. KGZ)")
    private String isoAlpha3;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @PrePersist
    @PreUpdate
    public void normalizeCodes() {
        if (isoAlpha2 != null) {
            isoAlpha2 = isoAlpha2.toUpperCase();
        }
        if (isoAlpha3 != null) {
            isoAlpha3 = isoAlpha3.toUpperCase();
        }
    }
}