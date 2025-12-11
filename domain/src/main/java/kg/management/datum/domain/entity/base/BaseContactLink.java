package kg.management.datum.domain.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import kg.management.datum.domain.entity.contact.ContactType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
public abstract class BaseContactLink<ID extends Serializable> extends MutableEntity<ID>  {
    @ManyToOne
    @JoinColumn(name = "type")
    private ContactType type;

    @Column(name = "is_primary", columnDefinition = "boolean default false")
    private boolean primary;

    @Column(name = "is_verified", columnDefinition = "boolean default false")
    private boolean verified;
}
