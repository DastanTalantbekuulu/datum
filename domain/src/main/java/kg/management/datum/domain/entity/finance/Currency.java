package kg.management.datum.domain.entity.finance;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.address.Country;
import kg.management.datum.domain.entity.base.LocalizedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "currency")
@AttributeOverride(name = "id", column = @Column(name = "code", length = 3, nullable = false, updatable = false, columnDefinition = "char(3)"))
public class Currency extends LocalizedEntity<String> {

    @Column(name = "symbol", nullable = false, length = 5)
    @NotNull
    @Size(max = 5)
    private String symbol;

    @Column(name = "decimals", nullable = false, columnDefinition = "smallint default 2")
    @Builder.Default
    private short decimals = 2;

    @ManyToMany(mappedBy = "currencies")
    @ToString.Exclude
    @Builder.Default
    private Set<Country> countries = new HashSet<>();
}
