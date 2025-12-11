package kg.management.datum.domain.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.LocalizedEntity;
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
@Table(name = "language")
@AttributeOverride(name = "id", column = @Column(name = "language", length = 2, nullable = false, updatable = false))
public class Language extends LocalizedEntity<String> {

    @Column(name = "native", nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String nameNative;
}