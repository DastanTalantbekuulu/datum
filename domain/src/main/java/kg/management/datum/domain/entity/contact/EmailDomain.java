package kg.management.datum.domain.entity.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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
@Table(name = "email_domain", indexes = @Index(name = "idx_email_domain", columnList = "name", unique = true))
public class EmailDomain extends BaseIdentityEntity {

    @Column(name = "name", nullable = false)
    private String name;
}
