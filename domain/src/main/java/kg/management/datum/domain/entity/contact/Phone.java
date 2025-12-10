package kg.management.datum.domain.entity.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.AbstractAuditMutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
public class Phone extends AbstractAuditMutable<Long> {

    @Column(name = "number", nullable = false, unique = true, length = 20)
    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 8, max = 20, message = "Phone number length is invalid")
    @Pattern(regexp = "^\\+[1-9]\\d{6,14}$", message = "Phone number must be in E.164 format (e.g. +996555123456)")
    private String number;

    @Column(name = "has_whatsapp", nullable = false, columnDefinition = "boolean default false")
    private boolean hasWhatsapp;

    @Column(name = "has_telegram", nullable = false, columnDefinition = "boolean default false")
    private boolean hasTelegram;
}