package kg.management.identity.entity.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kg.management.common.entity.base.LifecycleIdentityEntity;
import kg.management.identity.entity.storage.PhotoType;
import kg.management.identity.entity.storage.StorageFile;
import lombok.AllArgsConstructor;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "person_photo")
@SQLDelete(sql = "UPDATE person_photo SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class PersonPhoto extends LifecycleIdentityEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "storage_file_id", nullable = false)
    @ToString.Exclude
    private StorageFile storageFile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    @ToString.Exclude
    private PhotoType type;

    @Column(name = "is_primary", nullable = false, columnDefinition = "boolean default false")
    private boolean primary;
}