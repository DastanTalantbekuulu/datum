package kg.management.datum.domain.entity.dictionary;

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
@Table(name = "gender")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE gender SET deleted_at = NOW() WHERE code = ?")
@SQLRestriction("deleted_at IS NULL")
@AttributeOverride(name = "id", column = @Column(name = "code", length = 1, nullable = false))
@AttributeOverride(name = "extendI18n", column = @Column(name = "short_i18n", nullable = false))
@Audited
public class Gender extends AbstractI18nExtend<String> {
}