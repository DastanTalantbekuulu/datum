//package old.model;
//
//import old.config.Auditable;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
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
////@SQLDelete(sql = "update preiskurant set deleted=true where id=?")
//public class Preiskurant  extends Auditable<Long> implements Serializable {
//    @Column(columnDefinition = "TEXT")
//    private String description;
//    private Long service;
//    @JdbcTypeCode(SqlTypes.JSON)
//    private MonetaryAmount price;
//}
