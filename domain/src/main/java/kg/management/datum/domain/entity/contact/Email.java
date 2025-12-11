package kg.management.datum.domain.entity.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.BaseIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "email", uniqueConstraints = @UniqueConstraint(name = "uq_email_address", columnNames = {"local_part", "domain_id"}))
public class Email extends BaseIdentityEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "domain_id", nullable = false)
    private EmailDomain emailDomain;

    @NotBlank
    @Size(max = 64)
    @Column(name = "local_part", length = 64, nullable = false)
    private String localPart;

}