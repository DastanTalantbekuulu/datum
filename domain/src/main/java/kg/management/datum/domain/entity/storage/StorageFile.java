package kg.management.datum.domain.entity.storage;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.entity.base.AbstractAuditFull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "storage_file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE storage_file SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Audited
public class StorageFile extends AbstractAuditFull<Long> {

    @Column(name = "original_name", nullable = false)
    @NotBlank
    @Size(max = 255)
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
    private boolean isPublic;
}