package kg.management.datum.domain.entity.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.AbstractAuditMutable;
import kg.management.datum.domain.entity.geo.Country;
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
@Table(name = "phone_code")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE phone_code SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Audited
public class PhoneCode extends AbstractAuditMutable<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "prefix", nullable = false, length = 10)
    @NotBlank(message = "Phone prefix cannot be empty")
    @Size(max = 10, message = "Prefix cannot exceed 10 characters")
    private String prefix; // "996", "7"

    @Column(name = "mask", length = 50)
    @Size(max = 50)
    private String mask; // "(###) ##-##-##"

    @Column(name = "emoji", length = 5)
    @Size(max = 5)
    private String emoji;

    @Column(name = "regex")
    private String regex;

    @Column(name = "is_main", nullable = false)
    @Builder.Default
    private boolean main = true;
}