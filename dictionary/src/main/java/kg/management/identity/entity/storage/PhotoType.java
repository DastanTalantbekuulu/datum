package kg.management.identity.entity.storage;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.management.common.entity.base.LocalizedEntity;
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
@Table(name = "photo_type")
@AttributeOverride(name = "id", column = @Column(name = "code", length = 50, nullable = false))
public class PhotoType extends LocalizedEntity<String> {

    @Column(name = "description")
    private String description;
}