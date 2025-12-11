package kg.management.datum.domain.entity.contact;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.BaseIdentityEntity;
import lombok.AllArgsConstructor;
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
@Table(name = "phone", uniqueConstraints = @UniqueConstraint(name = "uq_phone_full", columnNames = {"phone_code_id", "number"}))
public class Phone extends BaseIdentityEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "phone_code_id", nullable = false)
    @ToString.Exclude
    private PhoneCode phoneCode;


    @Column(name = "number", nullable = false, length = 20, check = @CheckConstraint(name = "phone_number_check", constraint = "number ~ '^[0-9]{4,15}$'"))
    @NotBlank
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String number;

    @Column(name = "has_whatsapp", nullable = false, columnDefinition = "boolean default false")
    private boolean hasWhatsapp;

    @Column(name = "has_telegram", nullable = false,columnDefinition = "boolean default false")
    private boolean hasTelegram ;

    @Transient
    public String getFullNumber() {
        if (phoneCode == null || number == null) return null;
        return phoneCode.getPrefix() + number;
    }
}