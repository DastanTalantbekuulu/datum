package kg.management.datum.domain.entity.person;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kg.management.datum.domain.entity.base.AbstractAuditFull;
import kg.management.datum.domain.entity.dictionary.Gender;
import kg.management.datum.domain.entity.geo.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Table(name = "identity_document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE identity_document SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Audited
public class IdentityDocument extends AbstractAuditFull<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "document_type", nullable = false, length = 2)
    private String documentType; // P, I, V

    @Column(name = "doc_number", nullable = false, length = 50)
    private String docNumber;

    @Column(name = "serial", length = 20)
    private String serial;

    @Column(name = "authority")
    private String authority;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issuing_country_id", nullable = false)
    private Country issuingCountry;

    @Column(name = "mrz_string_1", length = 44)
    private String mrzString1;

    @Column(name = "mrz_string_2", length = 44)
    private String mrzString2;

    @Column(name = "mrz_string_3", length = 30)
    private String mrzString3;

    @Column(name = "mrz_surname", length = 100)
    private String mrzSurname;

    @Column(name = "mrz_given_names", length = 100)
    private String mrzGivenNames;

    @Column(name = "mrz_doc_number", length = 20)
    private String mrzDocNumber;

    @Column(name = "mrz_birth_date")
    private LocalDate mrzBirthDate;

    @Column(name = "mrz_expiry_date", nullable = false)
    private LocalDate mrzExpiryDate;

    @Column(name = "mrz_personal_number", length = 20)
    private String mrzPersonalNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mrz_sex_code")
    private Gender mrzSex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mrz_nationality_id")
    private Country mrzNationality;

    @Column(name = "is_primary", nullable = false, columnDefinition = "boolean default false")
    private boolean primary;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;
}