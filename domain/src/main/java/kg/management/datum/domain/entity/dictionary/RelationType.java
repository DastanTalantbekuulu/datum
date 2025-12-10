package kg.management.datum.domain.entity.dictionary;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.AbstractI18n;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "relation_type")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE relation_type SET deleted_at = NOW() WHERE code = ?")
@SQLRestriction("deleted_at IS NULL")
@AttributeOverride(name = "id", column = @Column(name = "code", length = 50, nullable = false,
        columnDefinition = "VARCHAR(50) CHECK (code ~ '^[A-Z_]+$' AND length(code) BETWEEN 3 AND 50)"))
@Audited
public class RelationType extends AbstractI18n<String> {
}