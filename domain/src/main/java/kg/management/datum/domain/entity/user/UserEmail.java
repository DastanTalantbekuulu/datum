package kg.management.datum.domain.entity.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.BaseContactLink;
import kg.management.datum.domain.entity.contact.Email;
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
@Table(name = "user_email")
public class UserEmail extends BaseContactLink<UserEmail.Id> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("emailId")
    @JoinColumn(name = "email_id")
    private Email email;

    public UserEmail(User user, Email email) {
        this.user = user;
        this.email = email;
        super.setId(new Id(user.getId(), email.getId()));
    }

    @Embeddable
    @Builder
    @Jacksonized
    public record Id(Long userId, Long emailId) implements Serializable {
    }
}
