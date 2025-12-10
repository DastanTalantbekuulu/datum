//package old.config;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.Column;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.Id;
//import jakarta.persistence.MappedSuperclass;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import java.io.Serializable;
//import java.time.Instant;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
////@Getter
////@Setter
////@ToString
//@Data
//@SuperBuilder
//@RequiredArgsConstructor
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
////@Where(clause = "deleted = false")
//public abstract class Auditable<U> implements Serializable {
//    @Id
//    private java.lang.Long id;
//
//    @CreatedBy
//    protected U createdBy;
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Instant creationDate;
//
//    @LastModifiedBy
//    protected U lastModifiedBy;
//
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Instant lastModifiedDate;
//
//    @JsonIgnore
//    @Column(columnDefinition = "boolean default false")
//    private Boolean deleted = false;
//
//}
