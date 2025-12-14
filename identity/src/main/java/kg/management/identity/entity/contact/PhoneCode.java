package kg.management.identity.entity.contact;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.common.entity.base.BaseIdentityEntity;
import kg.management.identity.entity.address.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "phone_code", indexes = {@Index(name = "idx_phone_code_prefix", columnList = "prefix"),
        @Index(name = "idx_phone_code_country", columnList = "country_id")})
public class PhoneCode extends BaseIdentityEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Country country;

    @Column(name = "prefix", nullable = false, length = 10,
            check = @CheckConstraint(name = "check_phone_code_prefix", constraint = "prefix ~ '^\\+[0-9]+$'"))
    @NotBlank(message = "Phone prefix cannot be empty")
    @Size(max = 10, message = "Prefix cannot exceed 10 characters")
    @Pattern(regexp = "^\\+[0-9]+$", message = "Prefix must start with '+' followed by digits (e.g. +996)")
    private String prefix; // "+996", "+7"

    @Column(name = "mask", length = 50)
    @Size(max = 50)
    private String mask; // "(###) ##-##-##"

    @Column(name = "emoji", length = 5)
    @Size(max = 5)
    private String emoji; // "🇰🇬"

    @Column(name = "regex")
    private String regex;

    @Column(name = "is_main", columnDefinition = "boolean default true")
    @Builder.Default
    private boolean main = true;
}