package kg.management.datum.domain.entity.finance;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.AbstractI18n;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE currency SET deleted_at = NOW() WHERE code = ?")
@SQLRestriction("deleted_at IS NULL")
@AttributeOverride(name = "id", column = @Column(name = "code", length = 3, nullable = false, updatable = false))
@Audited
public class Currency extends AbstractI18n<String> {
    @Column(name = "symbol", nullable = false, length = 5)
    @NotNull
    @Size(max = 5)
    private String symbol;

    @Column(name = "decimals", nullable = false, columnDefinition = "smallint default 2")
    @Builder.Default
    private Byte decimals = 2;
}
