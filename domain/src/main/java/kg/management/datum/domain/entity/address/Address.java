package kg.management.datum.domain.entity.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.embeddable.GeoCoordinates;
import kg.management.datum.domain.entity.base.LifecycleIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "address")
@SQLDelete(sql = "UPDATE address SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Address extends LifecycleIdentityEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private City city;

    @Column(name = "street_name", length = 150)
    @Size(max = 150)
    private String streetName;

    @Column(name = "building", length = 50)
    @Size(max = 50)
    private String building;

    @Column(name = "apartment", length = 20)
    @Size(max = 20)
    private String apartment;

    @Column(name = "floor", length = 10)
    @Size(max = 10)
    private String floor;

    @Column(name = "entrance", length = 10)
    @Size(max = 10)
    private String entrance;

    @Column(name = "postal_code", length = 20)
    @Size(max = 20)
    private String postalCode;

    @Column(name = "comment")
    private String comment;

    @Embedded
    @Builder.Default
    private GeoCoordinates coordinates = new GeoCoordinates();
}