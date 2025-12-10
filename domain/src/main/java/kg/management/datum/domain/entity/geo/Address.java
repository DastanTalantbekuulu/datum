package kg.management.datum.domain.entity.geo;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.GeoCoordinates;
import kg.management.datum.domain.entity.base.AbstractAuditFull;
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
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE address SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Audited
public class Address extends AbstractAuditFull<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "street_id", nullable = false)
    private Street street;

    @Column(name = "building", length = 50)
    private String building;

    @Column(name = "apartment", length = 20)
    private String apartment;

    @Column(name = "floor", length = 10)
    private String floor;

    @Column(name = "entrance", length = 10)
    private String entrance;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "comment")
    private String comment;

    @Embedded
    @Builder.Default
    private GeoCoordinates coordinates = new GeoCoordinates();
}