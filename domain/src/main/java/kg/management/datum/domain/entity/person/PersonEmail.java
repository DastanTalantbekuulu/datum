package kg.management.datum.domain.entity.person;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.contact.Email;
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
@Table(name = "person_email")
public class PersonEmail extends BaseContactLink<PersonEmail.Id> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("emailId")
    @JoinColumn(name = "email_id")
    private Email email;

    public PersonEmail(Person person, Email email) {
        this.person = person;
        this.email = email;
        super.setId(new Id(person.getId(), email.getId()));
    }

    @Embeddable
    @Builder
    @Jacksonized
    public record Id(Long personId, Long emailId) implements Serializable {
    }
}
