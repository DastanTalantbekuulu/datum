package datum.app.clinic.model;

import datum.app.admin.model.Post;
import datum.authenticate.User;
import datum.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Jacksonized
@SQLDelete(sql = "update employee set deleted=true where id=?")
@Where(clause = "deleted = false or deleted is null")
public class Employee  extends Auditable<Long> implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @JdbcTypeCode(SqlTypes.JSON)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
