package kg.management.identity.entity.person;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kg.management.common.entity.base.BaseIdentityEntity;
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
@Table(name = "person_relation", uniqueConstraints = @UniqueConstraint(name = "uq_person_relation", columnNames = {"person_id", "relative_id", "type"}),
        check = @CheckConstraint(name = "check_relation_self", constraint = "person_id <> relative_id"))
public class PersonRelation extends BaseIdentityEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "relative_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Person relative;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    @ToString.Exclude
    private RelationType type;

    @Column(name = "is_biological", columnDefinition = "boolean default true")
    @Builder.Default
    private Boolean biological = true;
}