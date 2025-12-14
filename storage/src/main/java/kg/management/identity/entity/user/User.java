package kg.management.identity.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.common.entity.base.LifecycleIdentityEntity;
import kg.management.identity.entity.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users_username_unique", columnNames = {"username", "deleted_at"}))
@SQLDelete(sql = "UPDATE users SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class User extends LifecycleIdentityEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", unique = true)
    private Person person;

    @Column(name = "external_id", unique = true, updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID externalId;

    @Column(name = "username", nullable = false, length = 64, check = @CheckConstraint(name = "check_username_format",
            constraint = "username ~ '^[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]$' AND username !~ '[._-]{2}'"))
    @Size(min = 3, max = 64, message = "Username must be between 3 and 64 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){1,62}[a-zA-Z0-9]$", message = "Username has invalid format")
    private String username;

    @Column(name = "password", length = 1024)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean enabled = true;

    @Column(name = "locked", nullable = false, columnDefinition = "boolean default false")
    private boolean locked;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "role"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles = new HashSet<>();
}