package kg.management.datum.domain.entity.dictionary;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.AbstractI18n;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "photo_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "code", length = 50, nullable = false))
@Audited
public class PhotoType extends AbstractI18n<String> {

    @Column(name = "description")
    private String description;
}