package kg.management.identity.entity.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.common.entity.base.LocalizedIdentityEntity;
import kg.management.identity.entity.Currency;
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
@Table(name = "country", uniqueConstraints = {@UniqueConstraint(name = "uq_country_iso2", columnNames = "iso_alpha2"),
        @UniqueConstraint(name = "uq_country_iso3", columnNames = "iso_alpha3")})
public class Country extends LocalizedIdentityEntity {

    @Column(name = "iso_alpha2", nullable = false, length = 2, columnDefinition = "char(2)")
    @Size(min = 2, max = 2, message = "ISO Alpha-2 code must be exactly 2 characters")
    @Pattern(regexp = "^[A-Z]{2}$", message = "ISO Alpha-2 must be uppercase letters (e.g. KG)")
    private String isoAlpha2;

    @Column(name = "iso_alpha3", nullable = false, length = 3, columnDefinition = "char(3)")
    @Size(min = 3, max = 3, message = "ISO Alpha-3 code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "ISO Alpha-3 must be uppercase letters (e.g. KGZ)")
    private String isoAlpha3;

    @Builder.Default
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    @ManyToMany
    @JoinTable(name = "country_currency", joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "currency_code", referencedColumnName = "code"))
    @ToString.Exclude
    @Builder.Default
    private Set<Currency> currencies = new HashSet<>();

    public void addCurrency(Currency currency) {
        currencies.add(currency);
        currency.getCountries().add(this);
    }

    public void removeCurrency(Currency currency) {
        currencies.remove(currency);
        currency.getCountries().remove(this);
    }

    @PrePersist
    @PreUpdate
    public void normalizeCodes() {
        if (isoAlpha2 != null) {
            isoAlpha2 = isoAlpha2.toUpperCase();
        }
        if (isoAlpha3 != null) {
            isoAlpha3 = isoAlpha3.toUpperCase();
        }
    }
}