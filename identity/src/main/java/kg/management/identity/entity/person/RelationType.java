package kg.management.identity.entity.person;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.common.entity.base.LocalizedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "relation_type")
@AttributeOverride(name = "id", column = @Column(name = "code", nullable = false, length = 50,
        check = @CheckConstraint(constraint = "code ~ '^[A-Z_]+$' AND length(code) BETWEEN 3 AND 50")))
public class RelationType extends LocalizedEntity<String> {
}