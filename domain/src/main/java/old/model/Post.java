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
//import org.hibernate.annotations.SQLDelete;
//
//import java.io.Serializable;
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
////@Entity
//@Jacksonized
////@SQLDelete(sql = "update post set deleted=true where id=?")
//public class Post  extends Auditable<Long> implements Serializable {
//    private String code;
//    private String en;
//    private String ru;
//    private String kg;
//    @Column(columnDefinition = "TEXT")
//    private String description;
//}
