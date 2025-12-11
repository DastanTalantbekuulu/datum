package kg.management.datum.domain.entity.person;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kg.management.datum.domain.entity.address.Country;
import kg.management.datum.domain.entity.base.LifecycleIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(name = "identity_document", indexes = {@Index(name = "idx_identity_doc_number", columnList = "doc_number"),
        @Index(name = "idx_identity_mrz_doc_number", columnList = "mrz_doc_number")},
        uniqueConstraints = @UniqueConstraint(name = "uq_id_doc", columnNames = {"issuing_country_id", "type", "doc_number", "deleted_at"}))
@SQLDelete(sql = "UPDATE identity_document SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class IdentityDocument extends LifecycleIdentityEntity {

    public enum Type {
        TD1,
        TD2,
        TD3,
        MRP,
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 3)
    private Type type;

    @Column(name = "doc_number", nullable = false, length = 50)
    private String docNumber;

    @Column(name = "serial", length = 20)
    private String serial;

    @Column(name = "authority")
    private String authority;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issuing_country_id", nullable = false)
    @ToString.Exclude
    private Country issuingCountry;

    @Column(name = "mrz_surname", length = 100)
    private String mrzSurname;

    @Column(name = "mrz_given_names", length = 100)
    private String mrzGivenNames;

    @Column(name = "mrz_doc_number", length = 20)
    private String mrzDocNumber;

    @Column(name = "mrz_birth_date")
    private LocalDate mrzBirthDate;

    @Column(name = "mrz_expiry_date")
    private LocalDate mrzExpiryDate;

    @Column(name = "mrz_personal_number", length = 20)
    private String mrzPersonalNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mrz_sex_code")
    @ToString.Exclude
    private Gender mrzSex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mrz_nationality_id")
    @ToString.Exclude
    private Country mrzNationality;

    @Column(name = "is_primary", nullable = false, columnDefinition = "boolean default false")
    private boolean primary;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;
}