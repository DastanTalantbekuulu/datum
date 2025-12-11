package kg.management.datum.domain.entity.person;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.contact.Phone;
import kg.management.datum.domain.entity.base.BaseContactLink;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Audited
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "person_phone")
public class PersonPhone extends BaseContactLink<PersonPhone.Id> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("phoneId")
    @JoinColumn(name = "phone_id")
    private Phone phone;

    public PersonPhone(Person person, Phone phone) {
        this.person = person;
        this.phone = phone;
        super.setId(new Id(person.getId(), phone.getId()));
    }

    @Embeddable
    @Builder
    @Jacksonized
    public record Id(Long personId, Long phoneId) implements Serializable {
    }
}
