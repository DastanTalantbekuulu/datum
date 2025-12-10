//package old.model;
//
//import old.config.Auditable;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import lombok.extern.jackson.Jacksonized;
//import org.hibernate.annotations.JdbcTypeCode;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.type.SqlTypes;
//
//import java.io.Serializable;
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
////@Entity
//@Jacksonized
////@SQLDelete(sql = "update employee set deleted=true where id=?")
//public class Employee  extends Auditable<Long> implements Serializable {
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Post post;
//
//    @JdbcTypeCode(SqlTypes.JSON)
//    private Schedule schedule;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
