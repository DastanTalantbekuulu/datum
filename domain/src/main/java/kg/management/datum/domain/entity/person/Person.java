package kg.management.datum.domain.entity.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.address.Country;
import kg.management.datum.domain.entity.base.LifecycleIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "person", indexes = {@Index(name = "idx_person_names", columnList = "last_name, first_name"),
        @Index(name = "idx_person_birth", columnList = "birth_date")})
@SQLDelete(sql = "UPDATE person SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Person extends LifecycleIdentityEntity {

    @Column(name = "last_name", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
    private String lastName;

    @Column(name = "first_name", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    @Size(max = 100)
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gender", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    @ToString.Exclude
    private Country citizenship;
}
