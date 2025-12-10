package kg.management.datum.domain.entity.user;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.AbstractI18nExtend;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "role")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE role SET deleted_at = NOW() WHERE role = ?")
@SQLRestriction("deleted_at IS NULL")
@AttributeOverride(name = "id", column = @Column(name = "role", length = 1, nullable = false))
@AttributeOverride(name = "extendI18n", column = @Column(name = "description_i18n", nullable = false))
@Audited
public class Role extends AbstractI18nExtend<String> {
}
