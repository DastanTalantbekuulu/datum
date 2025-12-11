package kg.management.datum.domain.entity.user;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.RichLocalizedEntity;
import lombok.AllArgsConstructor;
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
@Table(name = "role")
@AttributeOverride(name = "id", column = @Column(name = "role", length = 50, nullable = false))
@AttributeOverride(name = "extendI18n", column = @Column(name = "description_i18n", columnDefinition = "jsonb", nullable = false))
public class Role extends RichLocalizedEntity<String> {
}
