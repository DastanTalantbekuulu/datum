package kg.management.datum.domain.entity.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.BaseContactLink;
import kg.management.datum.domain.entity.contact.Phone;
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
@Table(name = "user_phone")
public class UserPhone extends BaseContactLink<UserPhone.Id> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("phoneId")
    @JoinColumn(name = "phone_id")
    private Phone phone;

    public UserPhone(User user, Phone phone) {
        this.user = user;
        this.phone = phone;
        super.setId(new Id(user.getId(), phone.getId()));
    }

    @Embeddable
    @Builder
    @Jacksonized
    public record Id(Long userId, Long phoneId) implements Serializable {
    }
}