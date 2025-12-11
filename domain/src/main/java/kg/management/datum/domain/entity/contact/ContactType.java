package kg.management.datum.domain.entity.contact;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.LocalizedEntity;
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
@Table(name = "contact_type")
@AttributeOverride(name = "id", column = @Column(name = "type", unique = true, length = 10))
public class ContactType extends LocalizedEntity<String> {
}
