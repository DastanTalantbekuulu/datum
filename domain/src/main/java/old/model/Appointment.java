//package old.model;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import old.config.Auditable;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import lombok.extern.jackson.Jacksonized;
//import org.hibernate.annotations.SQLDelete;
//
//import java.io.Serializable;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
////@Entity
//@Jacksonized
////@SQLDelete(sql = "update appointment set deleted=true where id=?")
//public class Appointment  extends Auditable<Long> implements Serializable {
//    private Boolean visible;
//    private String description;
//    private Instant start;
//    private Integer minute;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Employee employee;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Person person;
//
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "appointment_id")
//    private List<Treatment> treatments = new ArrayList<>();
//}
