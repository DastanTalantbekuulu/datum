package kg.management.datum.domain.entity.dictionary;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.AbstractI18n;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "contact_type")
@Getter
@Setter
@SQLDelete(sql = "UPDATE contact_type SET deleted_at = NOW() WHERE type = ?")
@SQLRestriction("deleted_at IS NULL")
@AttributeOverride(name = "id", column = @Column(name = "type", unique = true, length = 10))
@Audited
public class ContactType extends AbstractI18n<String> {
}
