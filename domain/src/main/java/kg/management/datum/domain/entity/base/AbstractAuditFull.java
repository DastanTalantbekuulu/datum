package kg.management.datum.domain.entity.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import kg.management.datum.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditFull<PK> extends  AbstractAuditMutable<PK> implements Auditable<User, PK, Instant> {

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    @Override
    public Optional<User> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    @Override
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<Instant> getCreatedDate() {
        return Optional.ofNullable(getCreatedAt());
    }

    @Override
    public void setCreatedDate(Instant creationDate) {
        setCreatedAt(creationDate);
    }

    @Override
    public Optional<User> getLastModifiedBy() {
        return Optional.ofNullable(updatedBy);
    }

    @Override
    public void setLastModifiedBy(User lastModifiedBy) {
        this.updatedBy = lastModifiedBy;
    }

    @Override
    public Optional<Instant> getLastModifiedDate() {
        return Optional.ofNullable(getUpdatedAt());
    }

    @Override
    public void setLastModifiedDate(Instant lastModifiedDate) {
        setUpdatedAt(lastModifiedDate);
    }
}
