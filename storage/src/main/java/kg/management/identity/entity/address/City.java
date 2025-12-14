package kg.management.identity.entity.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kg.management.common.entity.base.LocalizedIdentityEntity;
import kg.management.common.entity.embeddable.GeoCoordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "city", indexes = @Index(name = "idx_city_country_region", columnList = "country_id, region_id"))
public class City extends LocalizedIdentityEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    @ToString.Exclude
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @ToString.Exclude
    private Region region;
    @Builder.Default
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    @Embedded
    @Builder.Default
    private GeoCoordinates coordinates = new GeoCoordinates();
}