//package old.model;
//
//import old.config.Auditable;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
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
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
////@Entity
//@Jacksonized
////@SQLDelete(sql = "update department set deleted=true where id=?")
//public class Department  extends Auditable<Long> implements Serializable {
//
//    private String name;
//    @JdbcTypeCode(SqlTypes.JSON)
//    private Address address;
//
//    @OneToMany(cascade = {CascadeType.ALL})
//    @JoinColumn(name="departmentId")
//    private List<Employee> employees = new ArrayList<>();
//}
