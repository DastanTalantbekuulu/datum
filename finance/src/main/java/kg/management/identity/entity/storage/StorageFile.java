package kg.management.identity.entity.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.common.entity.base.LifecycleIdentityEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
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
@Table(name = "storage_file", uniqueConstraints = @UniqueConstraint(name = "uq_storage_file_path", columnNames = {"bucket", "path", "deleted_at"}))
@SQLDelete(sql = "UPDATE storage_file SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class StorageFile extends LifecycleIdentityEntity {

    @Column(name = "original_name", nullable = false, length = 1024)
    @NotBlank
    @Size(max = 1024)
    private String originalName;

    @Column(name = "mime_type", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String mimeType;

    @Column(name = "size", nullable = false)
    @NotNull
    private Long size;

    @Column(name = "bucket", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String bucket;

    @Column(name = "path", nullable = false, length = 512)
    @NotBlank
    @Size(max = 512)
    private String path;

    @Column(name = "is_public", nullable = false, columnDefinition = "boolean default false")
    private boolean publicAccess;
}